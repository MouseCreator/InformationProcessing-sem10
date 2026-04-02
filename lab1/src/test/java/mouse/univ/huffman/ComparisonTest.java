package mouse.univ.huffman;

import mouse.univ.FileManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparisonTest {

    @Test
    void compareSherlockHolmes() {
        frequencyTest(read("Sherlock.txt"));
    }

    @Test
    void compareCharlie() {
        frequencyTest(read("Charlie.txt"));
    }

    @Test
    void compareMario() {
        frequencyTest(read("Mario.txt"));
    }

    @Test
    void compareWonka() {
        frequencyTest(read("Wonka3.txt"));
    }

    @Test
    void compareScarlet() {
        frequencyTest(read("Scarlet.txt"));
    }

    @Test
    void compareBook() {
        frequencyTest(read("Book.txt"));
    }

    private String read(String filename) {
        return FileManager.readFromResources(filename);
    }
    private void frequencyTest(String paragraph) {
        TextFrequencyGenerator generator = new TextFrequencyGenerator();


        CharacterProbabilitySet charFreq = generator.generate(paragraph, false);
        charFreq.print(System.out);

        RegularEncoder regularEncoder = new RegularEncoder();
        CharacterEncoding regularEncoding = regularEncoder.encode(charFreq);
        int bitsRegular = regularEncoding.write(paragraph).length();
        System.out.println("Bits regular: " + bitsRegular);

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        CharacterEncoding huffmanEncoding = huffmanEncoder.encode(charFreq);
        int bitsHuffman = huffmanEncoding.write(paragraph).length();
        System.out.println("Bits Huffman: " + bitsHuffman);
        System.out.println("Text length: " + paragraph.length());
        System.out.println("Compression: " + (1.0 - (double) bitsHuffman / bitsRegular));

        Assertions.assertTrue(bitsRegular > bitsHuffman);
    }
}
