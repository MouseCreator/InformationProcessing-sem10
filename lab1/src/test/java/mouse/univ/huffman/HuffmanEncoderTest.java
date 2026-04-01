package mouse.univ.huffman;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncoderTest {

    private final Encoder encoder = new HuffmanEncoder();

    @Test
    void encodeExample() {
        DefinedFrequencyGenerator generator = new DefinedFrequencyGenerator();

        CharacterProbabilitySet probabilitySet = generator.generate(List.of(
                "A1", 0.4,
                "A2", 0.35,
                "A3", 0.2,
                "A4", 0.05
        ));
        CharacterEncoding actual = encoder.encode(probabilitySet);
        CharacterEncoding expected = new CharacterEncoding(Map.of(
            "A1", "0", "A2", "11", "A3", "101", "A4", "100"
        ));
        assertEquals(expected, actual);
    }
}