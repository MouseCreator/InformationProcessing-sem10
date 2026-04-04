package mouse.univ;

public class MatrixFactory {

    public static boolean[][] fromString(String matrix) {
        String[] initialSplit = matrix.split("\\n");
        int actualStrings = 0;
        for (String s : initialSplit) {
            if (!s.trim().isEmpty()) {
                actualStrings++;
            }
        }
        String[] split = new String[actualStrings];
        int k = 0;
        for (String s : initialSplit) {
            if (!s.trim().isEmpty()) {
                split[k++] = s;
            }
        }
        int rows = split.length;
        int columns = split[0].length();
        boolean[][] result = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char c = split[i].charAt(j);
                if (c == '0') {
                    result[i][j] = false;
                } else if (c == '1') {
                    result[i][j] = true;
                } else {
                    throw new IllegalArgumentException("Unexpected string");
                }
            }
        }
        return result;
    }
}
