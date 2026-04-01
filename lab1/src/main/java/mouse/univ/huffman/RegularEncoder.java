package mouse.univ.huffman;

import java.util.*;

public class RegularEncoder implements Encoder{



    @Override
    public CharacterEncoding encode(CharacterProbabilitySet probabilitySet) {
        Set<String> keys = probabilitySet.getProbabilityMap().keySet();
        int size = keys.size();
        HashMap<String, String> map = new HashMap<>();
        int digits = (int) Math.ceil(Math.log(size));
        String current = generateBase(digits);
        List<Entry> entries = probabilitySet.asSortedEntries();
        for (Entry e : entries) {
            map.put(e.getCharacter(), current);
            current = increment(current);
        }
        return new CharacterEncoding(map);
    }

    private String increment(String current) {
        int length = current.length();
        StringBuilder builder = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            if (current.charAt(i) == '0') {
                String before = current.substring(0, i);
                String after = builder.toString();
                return before.concat("1").concat(after);
            } else {
                builder.append('0');
            }
        }
        return generateBase(current.length());
    }

    private String generateBase(int digits) {
        return "0".repeat(Math.max(0, digits));
    }
}
