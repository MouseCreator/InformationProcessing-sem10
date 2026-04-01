package mouse.univ.huffman;

import java.util.HashMap;

public class TextFrequencyGenerator {
    public CharacterProbabilitySet generate(String inputText, boolean onlyAlphabetic) {
        int length = inputText.length();
        HashMap<String, Integer> occurs = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = inputText.charAt(i);
            if (onlyAlphabetic) {
                if (!Character.isAlphabetic(c)) {
                    continue;
                }
                c = Character.toLowerCase(c);
            }
            String key = String.valueOf(c);
            if (occurs.containsKey(key)) {
                occurs.compute(key, (k, prev) -> prev + 1);
            } else {
                occurs.put(key, 1);
            }
        }

        HashMap<String, Double> probabilities = new HashMap<>();
        double totalChars = occurs.values().stream().mapToDouble(i -> i).sum();
        for (String s : occurs.keySet()) {
            probabilities.put(s, occurs.get(s) / totalChars);
        }

        return new CharacterProbabilitySet(probabilities);
    }
}
