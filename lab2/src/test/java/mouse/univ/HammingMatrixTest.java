package mouse.univ;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HammingMatrixTest {

    private record Pair(int i, int j){
        @Override
        public String toString() {
            return "(" + i + "; " + j + ")";
        }
    }

    private String matrixToString(boolean[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                builder.append(matrix[i][j] ? "1" : "0").append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private String vectorToString(boolean[] verifiedInfo) {
        StringBuilder builder = new StringBuilder();
        for (boolean b : verifiedInfo) {
            builder.append(b ? "1" : "0").append(" ");
        }
        return builder.toString();
    }

    private void deepEquals(boolean[][] expected, boolean[][] actual) {
        Assertions.assertEquals(expected.length, actual.length, "Size mismatch");

        List<Pair> mismatches = new ArrayList<>();

        for (int i = 0; i < expected.length; i++) {
            boolean[] exp = expected[i];
            boolean[] act = actual[i];

            Assertions.assertEquals(exp.length, act.length);
            for (int j = 0; j < exp.length; j++) {
                if (exp[j] != act[j]) {
                    mismatches.add(new Pair(i, j));
                }
            }
        }

        if (!mismatches.isEmpty()) {
            String builder = "Found mismatch!\n" +
                    "Expected:\n" +
                    matrixToString(expected) +
                    "Actual:\n" +
                    matrixToString(actual) +
                    "List of pairs: " + mismatches;
            fail(builder);
        }
    }

    @Test
    void constructorCreateK3() {
        int k = 3;
        HammingMatrix matrix = new HammingMatrix(k);
        boolean[][] actual = matrix.getBits();

        String expectedStr = """
                0001111
                0110011
                1010101
                """;

        boolean[][] expected = MatrixFactory.fromString(expectedStr);
        deepEquals(expected, actual);
    }

    @Test
    void testMessageLength() {
        int k = 3;
        HammingMatrix matrix = new HammingMatrix(k);
        assertEquals(4, matrix.allowedMessageLength());
    }

    @Test
    void verifyAllK3() {
        int k = 3;
        HammingMatrix matrix = new HammingMatrix(k);
        for (int i = 0; i < 16; i++) {
            boolean[] number = Bits.bitWise(i, 4);
            boolean[] verifiedInfo = matrix.createVerification(number);
            assertTrue(matrix.verify(verifiedInfo));
            for (int j = 0; j < verifiedInfo.length; j++) {
                verifiedInfo[j] = !verifiedInfo[j];
                System.out.println(vectorToString(verifiedInfo));
                assertFalse(matrix.verify(verifiedInfo), "Passed verification when flipped bit " + j);
                verifiedInfo[j] = !verifiedInfo[j];
            }
        }
    }



    @Test
    void verifyAllK4() {
        int k = 4;
        HammingMatrix matrix = new HammingMatrix(k);
        System.out.println(matrixToString(matrix.getBits()));
        int length = matrix.allowedMessageLength();
        System.out.println("Message length: " + length);
        for (int i = 0; i < 1 << length; i++) {
            boolean[] number = Bits.bitWise(i, length);
            boolean[] verifiedInfo = matrix.createVerification(number);
            assertTrue(matrix.verify(verifiedInfo));
            for (int j = 0; j < verifiedInfo.length; j++) {
                verifiedInfo[j] = !verifiedInfo[j];
                assertFalse(matrix.verify(verifiedInfo));
                verifiedInfo[j] = !verifiedInfo[j];
            }
        }
    }

    @Test
    void verifyAllK5() {
        int k = 5;
        HammingMatrix matrix = new HammingMatrix(k);
        System.out.println(matrixToString(matrix.getBits()));
        int length = matrix.allowedMessageLength();
        System.out.println("Message length: " + length);
        boolean[] number = Bits.bitWise(10000, length);
        boolean[] verifiedInfo = matrix.createVerification(number);
        assertTrue(matrix.verify(verifiedInfo));
    }
}