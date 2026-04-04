package mouse.univ;

public class Block {
    private boolean[] bits;

    public Block(boolean[] bits) {
        this.bits = bits;
    }

    public boolean[] getBits() {
        return bits;
    }

    public int size() {
        return bits.length;
    }
}
