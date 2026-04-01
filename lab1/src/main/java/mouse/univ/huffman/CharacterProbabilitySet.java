package mouse.univ.huffman;

import lombok.Data;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Data
public class CharacterProbabilitySet {
    private final HashMap<String, Double> probabilityMap;

    public CharacterProbabilitySet(HashMap<String, Double> probabilityMap) {
        this.probabilityMap = probabilityMap;
    }

    public List<Entry> asSortedEntries() {
        List<Entry> entries = new ArrayList<>();
        for (String s : probabilityMap.keySet()) {
            Entry e = new Entry(s, probabilityMap.get(s));
            entries.add(e);
        }
        entries.sort(Comparator.comparingDouble(Entry::getFrequency));
        return entries;
    }

    public void print(PrintStream out) {
        List<Entry> sortedEntries = asSortedEntries();
        List<Entry> reversed = sortedEntries.reversed();
        for (Entry e : reversed) {
            String toPrint = e.getCharacter().concat(" ---> ") + e.getFrequency() + "\n";
            out.print(toPrint);
        }
    }
}
