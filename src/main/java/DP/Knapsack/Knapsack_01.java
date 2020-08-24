package DP.Knapsack;

public class Knapsack_01 {
    /*
    0-1 knapsack problem
    Recursion: A simple solution is to consider all subsets of items and calculate the total weight and value of
    all subsets. Consider the only subsets whose total weight is smaller than w. From all such subsets, pick the maximum value subset
    include or not include
     */

    public int knapsack(int W, int weight[], int val[], int n) {
        // base case
        if (n == 0 || W == 0) {
            return 0;
        }

        if (weight[n - 1] > W) {
            return knapsack(W, weight, val, n - 1);
        } else {
            return Math.max(val[n - 1] + knapsack(W - weight[n - 1], weight, val, n - 1),
                    knapsack(W, weight, val, n - 1));
        }


    }

    public int knapsack_bottomUp(int W, int weight[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0) {
            return 0;
        }

        // returns the maxmium value that can be put in a knapsack of capacity W
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i-1][w - weight[i - 1]],
                                        dp[i-1][w]);
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }

        }

        return dp[n][W];
    }

    /*
        slightly different
        How full you can fill this backpack
        no Value
     */
    public int backpack_bottomUp(int weight[], int W) {
        // Base Case
        if (weight.length == 0 || W == 0) {
            return 0;
        }

        // returns the maxmium value that can be put in a knapsack of capacity W
        int[][] dp = new int[weight.length + 1][W + 1];
        for (int i = 0; i <= weight.length + 1; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(weight[i - 1] + dp[i-1][w - weight[i - 1]],
                            dp[i-1][w]);
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }

        }

        return dp[weight.length][W];
    }

    public static void main(String args[]) {
        int val[] = new int[]{60, 100, 120};
        int weight[] = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(new Knapsack_01().knapsack(W, weight, val, n));
    }
}
