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
}