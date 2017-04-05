package algorithm;

public class FindUtil {
    public static int seqSearch(int[] a, int d) {
        for (int i = 0; i < a.length; i++) {
            if (d == a[i]) {
                return i + 1;
            }
        }
        return -1;
    }
}
