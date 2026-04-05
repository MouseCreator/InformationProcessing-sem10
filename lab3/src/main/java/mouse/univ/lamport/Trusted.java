package mouse.univ.lamport;

import mouse.univ.hash.KeyGenerator;

public class Trusted {


    private final KeyGenerator keyGenerator;
    private final int rounds;

    public Trusted(KeyGenerator keyGenerator, int rounds) {
        this.keyGenerator = keyGenerator;
        this.rounds = rounds;
    }

    public Key provisionKey() {
        String secret = keyGenerator.getSecretKey();
        return new Key(secret, rounds);
    }

    public void sendSecurely(Key key, ClientA clientA) {
        clientA.receiveKey(key);
    }

    public boolean send(Message message, ClientA clientA) {
        return clientA.receiveMessage(message);
    }
}
