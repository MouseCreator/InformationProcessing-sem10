package mouse.univ.huffman;

import lombok.Data;

@Data
public class HuffmanTreeNode {
    private String character;
    private HuffmanTreeNode left;
    private HuffmanTreeNode right;

    public HuffmanTreeNode(String character) {
        this.character = character;
        left = null;
        right = null;
    }

    public HuffmanTreeNode(String character, HuffmanTreeNode left, HuffmanTreeNode right) {
        this.character = character;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
