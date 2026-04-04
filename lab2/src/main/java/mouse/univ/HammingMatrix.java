package mouse.univ;

public class HammingMatrix {

    private final boolean[][] matrix;

    public HammingMatrix(int k) {
        if (k < 2) {
            throw new IllegalArgumentException("K should be greater than 1");
        }
        int numColumns = 1 << k - 1;
        int numRows = numColumns - k;
        matrix = new boolean[numRows][numColumns];
        for (int i = 0; i < numColumns; i++) {
            boolean[] column = bitWise(i+1, numRows);
            for (int j = 0; j < numRows; j++) {
                matrix[j][i] = column[j];
            }
        }
    }

    private boolean[] bitWise(int i, int len) {
        boolean[] column = new boolean[len];
        int k = 0;
        while (i > 0) {
            column[k] = i % 2 == 1;
            i = i / 2;
            k++;
        }
        return column;
    }

    public boolean[][] getBits() {
        return matrix;
    }

    public int getNumRows() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public Block createVerification(Block block) {
        boolean[] bits = block.getBits();
        boolean[] newBits = new boolean[getNumColumns()];
        int k = 0;
        for (int i = 1; i < newBits.length; i++) {
            if ((i + 1) % 2 == 1) {
                newBits[i] = bits[k++];
            }
        }
        for (int i = 0; i < newBits.length; i++) {
            if (i == 0 || (i + 1) % 2 == 0) {
                boolean verificationBit = createVerificationBit(newBits, i);
                newBits[i] = verificationBit;
            }
        }
        return new Block(newBits);
    }

    private boolean createVerificationBit(boolean[] newBits, int column) {
        int row = -1;
        for (int i = 0; i < getNumColumns(); i++) {
            if (matrix[i][column]) {
                row = i;
                break;
            }
        }
        if (row == -1) {
            throw new IllegalStateException("Cannot find verification row corresponding to column " + column);
        }
        boolean verifier = false;
        for (int i = 0; i < getNumColumns(); i++) {
            if (matrix[row][i]) {
                verifier ^= newBits[i];
            }
        }
        return verifier;
    }

    public boolean verify(Block block) {
        if (block.size() != getNumRows()) {
            throw new IllegalArgumentException("Mismatch of matrix and vector sizes");
        }
        Block error = multiplyBy(block);
        for (boolean bit : error.getBits()) {
            if (bit) {
                return false;
            }
        }
        return true;
    }

    private Block multiplyBy(Block block) {
        boolean[] bits = block.getBits();
        boolean[] error = new boolean[bits.length];
        for (int i = 0; i < getNumRows(); i++) {
            boolean current = false;
            for (int j = 0; j < getNumColumns(); j++) {
                current ^= matrix[i][j];
            }
            error[i] = current;
        }
        return new Block(error);
    }
}
