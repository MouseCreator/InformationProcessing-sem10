package mouse.univ.huffman;

import java.util.HashMap;
import java.util.List;

public class DefinedFrequencyGenerator {
    public CharacterProbabilitySet generate(List<Object> origin) {
        if (origin.size() % 2 == 1) {
            throw new IllegalArgumentException("Should be an even length list");
        }
        int index;
        HashMap<String, Double> map = new HashMap<>();
        for (index = 0; index < origin.size(); index +=2) {
            String str = (String) origin.get(index);
            Double freq = (Double) origin.get(index + 1);
            map.put(str, freq);
        }
        return new CharacterProbabilitySet(map);
    }
}
