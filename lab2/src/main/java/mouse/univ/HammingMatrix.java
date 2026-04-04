package mouse.univ;

public class HammingMatrix {

    private final boolean[][] matrix;

    public HammingMatrix(int k) {
        if (k < 2) {
            throw new IllegalArgumentException("K should be greater than 1");
        }
        int numColumns = (1 << k) - 1;
        int numRows = k;
        matrix = new boolean[numRows][numColumns];
        for (int i = 0; i < numColumns; i++) {
            boolean[] column = Bits.bitWiseReverse(i+1, numRows);
            for (int j = 0; j < numRows; j++) {
                matrix[j][i] = column[j];
            }
        }
    }

    public int allowedMessageLength() {
        return matrix[0].length - matrix.length;
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

    public boolean[] createVerification(boolean[] bits) {
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
        return newBits;
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

    public boolean verify(boolean[] block) {
        if (block.length != getNumColumns()) {
            throw new IllegalArgumentException("Mismatch of matrix and vector sizes. Columns: " + getNumColumns() + ". Size: " + block.length);
        }
        boolean[] error = multiplyBy(block);
        for (boolean bit : error) {
            if (bit) {
                return false;
            }
        }
        return true;
    }

    private boolean[] multiplyBy(boolean[] bits) {
        boolean[] error = new boolean[bits.length];
        for (int i = 0; i < getNumRows(); i++) {
            boolean current = false;
            for (int j = 0; j < getNumColumns(); j++) {
                if (matrix[i][j]) {
                    current ^= bits[j];
                }
            }
            error[i] = current;
        }
        return error;
    }
}
