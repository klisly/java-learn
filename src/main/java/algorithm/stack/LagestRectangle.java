package algorithm.stack;

/**
 Given n non-negative integers representing the histogram's bar height where
 the width of each bar is 1, find the area of largest rectangle in the histogram.
 Above is a histogram where width of each bar is 1, given height =[2,1,5,6,2,3].
 The largest rectangle is shown in the shaded area, which has area =10 unit.
 For example,
 Given height = [2,1,5,6,2,3],
 return 10.
 */
public class LagestRectangle {

    public static void main(String[] args){
        int[] height = {2,1,5,6,2,3};
        /**
         使用动态规划，用left[i]表示第i个柱子可以最多向左延伸至第
         left[i]个柱子，形成一个矩形，right[i]则表示向右延伸。
         遍历两次，分别计算出这两个数组。
         */
        System.out.println(getMaxRectangle(height));
    }

    private static int getMaxRectangle(int[] height) {
        int ans = 0;
        int n = height.length;
        int left[] = new int[n];
        int right[] = new int[n];
        processLR(height, left, right);
        return 0;
    }

    private static void processLR(int[] height, int[] left, int[] right) {
        int n = height.length;
        int[] tmp = new int[n+2];
        tmp[0] = -1;
        for(int i = 0; i <= n; i++){
//            tmp[i] =
        }
    }
}
