package mouse.univ.lamport;

import mouse.univ.hash.HashFunction;

public class ClientA {

    private int iA;
    private final HashFunction hashFunction;
    private int rounds;
    private String secret;

    public ClientA(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        iA = 0;
    }

    public void receiveKey(Key key) {
        this.secret = key.getSecret();
        this.rounds = key.getRounds();
        iA = 1;
    }

    public boolean receiveMessage(Message message) {
        String hash = message.getHash();
        String verifiedHash = hashFunction.hash(hash);
        if (iA >= rounds) {
            return false;
        }
        if (verifiedHash.equals(secret) && iA == message.getI()) {
            iA++;
            this.secret = verifiedHash;
            return true;
        } else {
            return false;
        }
    }
}
