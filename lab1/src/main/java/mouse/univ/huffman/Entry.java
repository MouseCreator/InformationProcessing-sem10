package mouse.univ.huffman;

import lombok.Data;

@Data
public class Entry {
    private String character;
    private Double frequency;

    public Entry(String character, Double frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}
