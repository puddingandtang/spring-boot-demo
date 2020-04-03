package com.tcl.demo.boot.common.test.learn.arithmetic.dynamicProgram;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 例子：
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class ChildArrayMaxValue {

    @Test
    public void test() {

        int[] nums = {1, 2, 3, -1, -2, -2, -1, 7};

        System.out.println(maxSubArray(nums));


    }

    public static int maxSubArray(int[] nums) {

        int n = nums.length, maxSum = nums[0];

        for (int i = 1; i < n; ++i) {

            /**
             * 这里需要理解，也视为核心，为什么这样写：
             * dp[i] = max(a[i],a[i]+dp[i-1])
             * 这个看起来不好理解：
             * 我们将dp[i] 作为这i下标结尾的0到i下标的区间的求和(必须理解这句话)，
             * 例子
             * {1,2} => dp[2] = 3
             * {1,-3} => dp[2] = 1，因为1+(-3) = -2 ，比1小
             * 其实随着数组的扩大，假设加了一个元素3，
             * {1,2,3} => dp[3] = 6
             * {1,-3,3} => dp[3] = 3
             *
             * 一个子数组加起来为a，如果子数组后再增加元素b，那么如果b>0 ,则a+b则大反之相加比a小
             *
             */
            // 最好debug走一遍，如果不理解
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];

            maxSum = Math.max(nums[i], maxSum);
        }

        return maxSum;
    }


}
