package mouse.univ.huffman;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CharacterEncoding {
    private final HashMap<String, String> encoding;

    public CharacterEncoding(Map<String, String> encoding) {
        this.encoding = new HashMap<>(encoding);
    }

    public String write(String chars) {
        List<String> charList = new ArrayList<>();
        for (Character c : chars.toCharArray()) {
            charList.add(String.valueOf(c));
        }
        return write(charList);
    }

    public String write(List<String> chars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String t : chars) {
            String s = encoding.get(t);
            if (s == null) {
                stringBuilder.append("!");
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }
}
