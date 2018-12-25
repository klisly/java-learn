package algorithm.dynamicplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinMatrixPath {
    /**
     * 给定一个矩阵m，从左上角开始每次只能向右走或者向下走，最后达到右下角的位置，路径中所有数字累加起来就是路径和，
     * 返回所有路径的最小路径和，如果给定的m如下，那么路径1,3,1,0,6,1,0就是最小路径和，返回12.
     * <p>
     * 1 3 5 9
     * <p>
     * 8 1 3 4
     * <p>
     * 5 0 6 1
     * <p>
     * 8 8 4 0
     */
    static List<List<Integer>> list;
    static Integer[][] dp;

    public static void main(String[] args) {
        for(int i = 1; i < 10; i++){
            compute(i);
        }
    }

    private static void compute(int n) {
        dp = new Integer[n][n];
        genMatrix(n);
        System.out.println(minMatrixPath(n - 1, n - 1));
    }

    private static Integer minMatrixPath(int x, int y) {
        if (dp[x][y] != null) {
            return dp[x][y];
        }
        if (x > 0 && y > 0) {
            dp[x][y] = Math.min(minMatrixPath(x - 1, y), minMatrixPath(x, y - 1)) + list.get(x).get(y);
        }
        if (x == 0 && y > 0) {
            dp[x][y] = minMatrixPath(x, y - 1) + list.get(x).get(y);
        }
        if (x > 0 && y == 0) {
            dp[x][y] = minMatrixPath(x - 1, y) + list.get(x).get(y);
        }
        if (x == 0 && y == 0) {
            dp[x][y] = list.get(x).get(y);
        }
        return dp[x][y];
    }

    private static void genMatrix(int n) {
        Random random = new Random();
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int num = random.nextInt(15);
                nums.add(num);
                System.out.print(num + "\t");
            }
            list.add(nums);
            System.out.println();

        }
    }
}
