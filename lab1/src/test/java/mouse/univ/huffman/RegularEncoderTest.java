package mouse.univ.huffman;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RegularEncoderTest {

    @Test
    void encode() {
        Encoder encoder = new RegularEncoder();
        DefinedFrequencyGenerator generator = new DefinedFrequencyGenerator();

        CharacterProbabilitySet probabilitySet = generator.generate(List.of(
                "A1", 0.01,
                "A2", 0.02,
                "A3", 0.03,
                "A4", 0.04
        ));
        CharacterEncoding actual = encoder.encode(probabilitySet);
        CharacterEncoding expected = new CharacterEncoding(Map.of(
                "A1", "00", "A2", "01", "A3", "10", "A4", "11"
        ));
        assertEquals(expected, actual);
    }
}