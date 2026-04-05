package mouse.univ.lamport;

import lombok.Data;

@Data
public class Key {
    private String secret;
    private int rounds;

    public Key(String secret, int rounds) {
        this.secret = secret;
        this.rounds = rounds;
    }
}
