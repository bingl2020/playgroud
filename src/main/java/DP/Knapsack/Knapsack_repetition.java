package DP.Knapsack;

/*
https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/?ref=rp

dp[i] = max(dp[i], dp[i - wt[j]] + val[j]);
unlimited number of an item.

Why this is 1D array problem and classic knapsack is 2D ????
because for this problem, the number of items never change, for classic, it is changed, so we need to have another D to control items to select.

 */
public class Knapsack_repetition {

    public int unboundedKnapsack(int W, int n, int[] val, int[] wt) {
        int[] dp = new int[W + 1];

        for (int w = 0; w <= W; w++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - wt[j]] + val[j]);
                }
            }
        }

        return dp[W];
    }

    /*
    Given n items with sizenums[i]which an integer array and all positive numbers.
    An integertargetdenotes the size of a backpack. Find the number of possible fill the backpack.
     */

    public int backPackV(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[target];
    }

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


    public static void main(String[] args) {
        int W = 100;
        int val[] = {10, 30, 20};
        int wt[] = {5, 10, 15};
        int n = val.length;
        System.out.println(new Knapsack_repetition().unboundedKnapsack(W, n, val, wt));


        int target = 7;
        int[] items = {7,3,2,1};

        System.out.println(new Knapsack_repetition().backPackV(items,7));

    }
}
