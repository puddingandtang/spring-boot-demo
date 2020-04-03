package com.tcl.demo.boot.common.test.learn.arithmetic.dynamicProgram;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 */
public class ClimbStairs {

    // f(i,n) = f(i+1,) + f(i+2,n)

    public static void main(String[] args) {

        System.out.println(way1(0,5));
        System.out.println(way2(5));

    }


    /**
     * 方法1：假设当前阶梯为i，那么下一步可以由 f(i + 1) + f(i + 2)组成
     */

    public static int way1(int curIdx, int n) {

        if (curIdx > n) {

            return 0;
        }

        if (curIdx == n) {

            return 1;
        }

        return way1(curIdx + 1, n) + way1(curIdx + 2, n);
    }

    /**
     * 方法2：
     * 第x阶梯      组合
     * 1             1
     * 2             1+1，2
     * 3             1+1+1，1+2，2+1
     * 4             1+1+1+1，1+1+2，1+2+1，2+1+1，2+2
     * 5             1+1+1+1+1，1+1+1+2，1+1+2+1，1+2+1+1，2+1+1+1，2+2+1，1+2+2，2+1+2
     *
     * 那么到第i阶梯的方法总和为 dp(i) = dp(i-1) + dp(i-2) 的和
     * 动态规划
     */
    public static int way2(int n){

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];


    }


}
