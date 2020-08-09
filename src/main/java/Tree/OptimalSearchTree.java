package Tree;

import java.util.Arrays;

/*
 1 2 3 4 5 6
 2 3 4 5 6

 */
public class OptimalSearchTree {

    public int optimalSearchTree(int[] key, int[] freq, int n) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], -1);
        }

        return optimalSearchTree(freq, 0, n - 1, cost);
    }

    public int optimalSearchTree(int[] freq, int i, int j, int[][] costMemo) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return freq[i];
        }

        if (costMemo[i][j] != -1) {
            return costMemo[i][j];
        }

        int sum = sum(freq, i, j);
        int minCost = Integer.MAX_VALUE;
        for (int root = i; root <= j; root++) {
            int cost = optimalSearchTree(freq, i, root - 1, costMemo)
                    + optimalSearchTree(freq, root + 1, j, costMemo);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        return costMemo[i][j] = minCost + sum;
    }

    private int sum(int[] freq, int i, int j) {
        int sum = 0;
        for (int root = i; root <= j; root++) {
            sum += freq[root];
        }
        return sum;
    }

    public static void main(String[] args) {

        int keys[] = {0, 1, 2, 3, 4, 5, 6};
        int freq[] = {22, 18, 20, 5, 25, 2, 8};
        int n = keys.length;
        System.out.println("Cost of Optimal BST is "
                + new OptimalSearchTree().optimalSearchTree(keys, freq, n));

        System.out.println("Tabulation - Cost of Optimal BST is "
                + new OptimalSearchTree().optimalSearchTree_tabulation(keys, freq, n));
    }

    public int optimalSearchTree_tabulation(int[] key, int[] freq, int n) {
        int[][] dp = new int[n][n];


        for (int i = 0; i < n; i++) {
            dp[i][i] = freq[i];
        }


        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len -1;
                System.out.println("i  " + i + " j " + j);
                int sum = sum(freq, i, j);

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <=j; k++) {
                    int val = sum
                            + (k - 1 >= i ? dp[i][k-1] : 0)
                            + (k + 1 <= j ? dp[k+1][j] : 0);
                    if (val < dp[i][j]) {
                        dp[i][j] = val;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }


}