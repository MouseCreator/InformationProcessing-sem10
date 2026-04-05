package mouse.univ.lamport;

import lombok.Data;

@Data
public class Message {
    private final String hash;
    private final int i;

    public Message(String hash, int i) {
        this.hash = hash;
        this.i = i;
    }
}
