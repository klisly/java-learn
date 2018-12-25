package algorithm.dynamicplan;

import java.math.BigInteger;

public class ClimbStair {
    // 爬楼梯问题
    // 有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法。
    /**
     * 分析：动态规划的实现的关键在于能不能准确合理的用动态规划表来抽象出 实际问题。在这个问题上，我们让f(n)表示走上n级台阶的方法数。
     * 那么当n为1时，f(n) = 1,n为2时，f(n) =2,就是说当台阶只有一级的时候，方法数是一种，台阶有两级的时候，方法数为2。那么当我们要走上n级台阶，
     * 必然是从n-1级台阶迈一步或者是从n-2级台阶迈两步，所以到达n级台阶的方法数必然是到达n-1级台阶的方法数加上到达n-2级台阶的方法数之和。
     * 即f(n) = f(n-1)+f(n-2)，我们用dp[n]来表示动态规划表，dp[i],i>0,i<=n,表示到达i级台阶的方法数。
     **/
    static BigInteger[] dp;

    public static void main(String[] args) {
//        for(int i = 1; i < 1024;i++){
//            tryCount(i);
//        }
        int n = 1024;
        tryCount(n);
        for (int i = n; i > 0; i--) {
            System.out.println(dp[i - 1]);
        }
    }

    private static void tryCount(int n) {
        dp = new BigInteger[n];
        coutStep(n);

    }

    static BigInteger coutStep(int n) {
        if (dp[n - 1] != null) {
            return dp[n - 1];
        }
        if (n == 1) {
            dp[n - 1] = new BigInteger("1");
            return dp[n - 1];
        }
        if (n == 2) {
            dp[n - 1] = new BigInteger("2");
            return dp[n - 1];
        }
        if (n > 2) {
            dp[n - 1] = coutStep(n - 1).add(coutStep(n - 2));
        }
        return dp[n - 1];
    }
}
