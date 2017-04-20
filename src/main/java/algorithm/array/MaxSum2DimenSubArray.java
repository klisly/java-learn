package algorithm.array;

/**
 假设已经确定了矩形区域的上下边界，比如知道矩形区域的上下边界分别是第a行和第c行，
 现在要确定左右边界。
 其实这个问题就是一维的，可以把每一列中第a行和第c行之间的元素看成一个整体。
 即求数组(BC[1], ..., BC[M])中和最大的一段，其中BC[i]=B[a][i] + ... + B[c][i]。
 这样，我们枚举矩形的上下边界，然后再用一维情况下的方法确定左右边界，就可以得到二维问题的解。
 新的方法的时间复杂度为O(N*M*min(N, M))。
 */
public class MaxSum2DimenSubArray {
    public static void main(String[] args) {
        int[][] h = {{1, 2, 3}, {4, -5, 6}, {7, 8, 9}};
        System.out.println(sum2DimenArray(h));
    }

    public static int sum2DimenArray(int[][] arr) {
        int maxumum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                int[] n = new int[arr[0].length]; // 二维转换一维
                for (int w = 0; w < arr[0].length; w++) {
                    sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum = sum + arr[k][w];
                    }
                    n[w] = sum;
                }
                if (maxSum(n) > maxumum)
                    maxumum = maxSum(n);
            }
        }
        return maxumum;
    }
    // max[i] = max(max[i-1] + arr[i], arr[i])
    public static int maxSum(int[] arr) {
        int sum = arr[0];
        int tmp;
        for (int i = 1; i < arr.length; ++i) {
            tmp = sum + arr[i];
            if(tmp < arr[i]){
                sum = arr[i];
            } else {
                sum = tmp;
            }
        }
        return sum;
    }

}
