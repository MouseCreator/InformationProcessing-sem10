package mouse.univ.huffman;

import lombok.Data;

import java.util.*;

public class HuffmanEncoder implements Encoder {

    @Data
    private static class Entry {
        private final String character;
        private final Double frequency;
        private final HuffmanTreeNode huffmanTreeNode;

        public Entry(String character, Double frequency) {
            this.frequency = frequency;
            this.character = character;
            this.huffmanTreeNode = new HuffmanTreeNode(this.character);
        }

        public Entry(Entry e1, Entry e2) {
            this.frequency = e1.frequency + e2.frequency;
            this.character = e1.character.concat(e2.character);
            huffmanTreeNode = new HuffmanTreeNode(this.character, e1.huffmanTreeNode, e2.huffmanTreeNode);
        }
    }

    @Override
    public CharacterEncoding encode(CharacterProbabilitySet probabilitySet) {
        List<Entry> entryList = toSortedEntryList(probabilitySet);
        Entry singleEntry = toSingleEntry(entryList);
        return toHuffmanEncoding(singleEntry);
    }

    private CharacterEncoding toHuffmanEncoding(Entry singleEntry) {
        HashMap<String, String> map = new HashMap<>();
        recursiveEncoder(map, "", singleEntry.huffmanTreeNode);
        return new CharacterEncoding(map);
    }

    private void recursiveEncoder(HashMap<String, String> encodingMap, String binaryPrefix, HuffmanTreeNode current) {
        if (current.isLeaf()) {
            encodingMap.put(current.getCharacter(), binaryPrefix);
            return;
        }
        HuffmanTreeNode left = current.getLeft();
        if (left != null) {
            recursiveEncoder(encodingMap, binaryPrefix.concat("0"), left);
        }
        HuffmanTreeNode right = current.getRight();
        if (right != null) {
            recursiveEncoder(encodingMap, binaryPrefix.concat("1"), right);
        }
    }

    private Entry toSingleEntry(List<Entry> entryList) {
        while (entryList.size() > 1) {
            Entry first = entryList.removeFirst();
            Entry second = entryList.removeFirst();
            Entry newEntry = new Entry(first, second);
            int i = Collections.binarySearch(entryList, newEntry, Comparator.comparingDouble(e -> e.frequency));
            int toInsert;
            if (i >= 0) {
                toInsert = i;
            } else {
                toInsert = -i - 1;
            }
            entryList.add(toInsert, newEntry);
        }
        return entryList.getFirst();
    }

    private List<Entry> toSortedEntryList(CharacterProbabilitySet probabilitySet) {
        HashMap<String, Double> probabilityMap = probabilitySet.getProbabilityMap();
        List<Entry> entries = new ArrayList<>();
        for (String s : probabilityMap.keySet()) {
            Entry e = new Entry(s, probabilityMap.get(s));
            entries.add(e);
        }
        entries.sort(Comparator.comparingDouble(e -> e.frequency));
        return entries;
    }
}
