package DP;

/*
Given an array of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.For example,

Input:
[2, 4, 6, 2, 5]
Output:
13
Explanation:
Since we pick 2, 6, and 5.
 */
public class LargestSumOfNonAdjacentNumbers {

    public int sum(int[] a) {

        return sumUtil(a, 0);
    }

    public int sumUtil(int[] a, int index) {
        if (index >= a.length) {
            return 0;
        }

        return Math.max(a[index] + sumUtil(a, index + 2), sumUtil(a, index + 1));
    }

    private int sumDP(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        if (a.length == 1) {
            return a[0];
        }

        if (a.length == 2) {
            return Math.max(a[0], a[1]);
        }
        int max = Integer.MIN_VALUE;
        int[]dp = new int[a.length];
        for (int i = 2; i < a.length; i++) {
            dp[i] = Math.max(a[i] + dp[i - 1], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }

        return max;

    }

}

