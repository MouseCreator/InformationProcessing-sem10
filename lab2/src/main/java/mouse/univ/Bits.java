package mouse.univ;

public class Bits {
    public static boolean[] bitWise(int i, int len) {
        boolean[] column = new boolean[len];
        int k = 0;
        while (i > 0) {
            column[k] = i % 2 == 1;
            i = i / 2;
            k++;
        }
        return column;
    }

    public static boolean[] bitWiseReverse(int i, int len) {
        return reverse(bitWise(i, len));
    }

    public static boolean[] reverse(boolean[] column) {
        boolean[] copy = new boolean[column.length];
        for (int i = 0; i < column.length; i++) {
            copy[column.length-1-i] = column[i];
        }
        return copy;
    }

}
