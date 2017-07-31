package algorithm.array;

import algorithm.SortUtil;

/**
 统计0~n之间的数字二进制的1的个数
 题目描述：
 给定一个数字n,统计0~n之间的数字二进制的1的个数，并用数字输出
 例子：
 当n = 5 时 返回的结果应该是：[0,1,1,2,1,2]
 要求：
 算法复杂度o (n)
 空间复杂度o (n)
 思路分析：
 根据题目的要求，时间和空间复杂度，明显是要用动态规划的方法
 得出推到公式：f(n) = 不大于f(n)的最大的2的次方+f(k),k一定是再前面出现的，
 用数组记录，直接查询
 举例 f(5) = f(4) + f(1), 注意2的次方都是一个1，而且是最高位，f(5) = 1 + f(1), f(6) = 1 + f(2)直到f(8) = 1
 f(n) = f(n) + (n的位数未)
 */
public class OneNumIn0ToN {

    public static void main(String[] args){
        int[] arr = countBit(17);
        String a = "s", b="2";
        a.equalsIgnoreCase(b);
        SortUtil.out(arr);
    }

    private static int[] countBit(int n) {
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i <= n; i++){
            if(i % 2 == 0){
                arr[i] = arr[i/2];
            } else {
                arr[i] = arr[i / 2] + 1;
            }
        }
        return arr;
    }
}
