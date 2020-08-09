package DP;
/*
Tags: coin change problem, dynamic programming
Given a number S and coins of values V = {v1, v2,v3, v4}. Find the number of ways change can be made for S using these coins.
We have an infinite supply of these coins. Commonly, this problem is known as the coin change problem. For example,
 */

/*
v = 1 2 5

10 - 1 -> 9 - 1 -> 8 - 5 -> 3 -1 -> 2 - 2 -> result
10 - 2 -> 8 - 5 -> 3 -1 -> 2 - 2 -> result

c(S, V) = c(i, j-1)
                            10
                   9, 1             10, 2
              8, 1     9, 2      8, 2   10, 5
         7, 1   8, 2

    1   2   3   4   5
1   1   1   1   1   1
2   1   2   2  1+
5

 */
public class CoinChangeNumberOfWaysProblem {

    // number of ways
    public int coinChangeNumberOfWays(int value, int[] coins, int index) {
        if (value < 0) {
            return 0;
        }
        if (value == 0) {
            return 1;
        }

        if (index >= coins.length) {
            return 0;
        }

        return coinChangeNumberOfWays(value, coins, index + 1)
                + coinChangeNumberOfWays(value - coins[index], coins, index);
    }

    /*
        0   1   2   3   4   5
     0  1   0   0   0   0   0
     1  1
     2  1
     5  1
     */

    public int coinChangeNumberOfWaysDP(int value, int[] coins) {
        int[][] dp = new int[coins.length + 1][value + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= value; j++) {
                dp[i][j] = dp[i - 1][j]    // not include
                        + (j >= coins[i-1] ? dp[i][j - coins[i-1]]: 0);
            }
        }

        return dp[coins.length][value];
    }

    //fewest count

}
