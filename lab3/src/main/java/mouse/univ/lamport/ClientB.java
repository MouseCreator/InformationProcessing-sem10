package mouse.univ.lamport;

import lombok.Setter;
import mouse.univ.hash.HashFunction;

public class ClientB {

    @Setter
    private ClientA clientA;
    private final Trusted trusted;

    private String secret;
    private int rounds;
    private final HashFunction hashFunction;
    private int iB;

    public ClientB(Trusted trusted, HashFunction hashFunction) {
        this.trusted = trusted;
        this.hashFunction = hashFunction;
        clientA = null;
    }

    public void initialize() {
        Key key = trusted.provisionKey();
        this.secret = key.getSecret();
        this.rounds = key.getRounds();
        String hash = computeHash(this.rounds);
        Key aKey = new Key(hash, this.rounds);
        trusted.sendSecurely(aKey, clientA);
        this.secret = hash;
        iB = 1;
    }

    public String computeHash(int times) {
        String val = secret;
        for (int i = 0; i < times; i++) {
            val = hashFunction.hash(val);
        }
        return val;
    }

    public int remainingRounds() {
        return rounds - iB;
    }

    public boolean sendMessage() {
        String hash = computeHash(this.rounds - iB);
        Message message = new Message(hash, iB);
        return trusted.send(message, clientA);
    }
}
