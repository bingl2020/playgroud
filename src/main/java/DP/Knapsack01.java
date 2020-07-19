package DP;

import java.util.HashMap;
import java.util.Map;

public class Knapsack01 {

    public int bottomUpDP2(int val[], int wt[], int W){
        int[][] dp = new int[val.length + 1][W + 1];

        for (int i = 0; i < dp.length; i ++) {
            for (int w = 0; w <= W; w++ ) {
                if (i == 0 || w == 0) {
                  //  dp[i][w] = 0;
                    continue;
                }
                if (w - wt[i-1] >= 0) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + wt[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[val.length][W];
    }

    /**
     * Solves 0/1 knapsack in bottom up dynamic programming
     */
    public int bottomUpDP(int val[], int wt[], int W){
        int K[][] = new int[val.length+1][W+1];
        for(int i=0; i <= val.length; i++){
            for(int j=0; j <= W; j++){
                if(i == 0 || j == 0){
                    K[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    K[i][j] = Math.max(K[i-1][j], K[i-1][j-wt[i-1]] + val[i-1]);
                }else{
                    K[i][j] = K[i-1][j];
                }
            }
        }
        return K[val.length][W];
    }

    /**
     * Key for memoization
     */
    class Index {
        int remainingWeight;
        int remainingItems;
    }

    /**
     * Solves 0/1 knapsack in top down DP
     */
    public int topDownRecursive(int values[], int weights[], int W) {
        //map of key(remainingWeight, remainingCount) to maximumValue they can get.
        Map<Index, Integer> map = new HashMap<>();
        return topDownRecursiveUtil(values, weights, W, values.length, 0, map);
    }

    public int topDownRecursiveUtil(int values[], int weights[], int remainingWeight, int totalItems, int currentItem, Map<Index, Integer> map) {
        //if currentItem exceeds total item count or remainingWeight is less than 0 then
        //just return with 0;
        if(currentItem >= totalItems || remainingWeight <= 0) {
            return 0;
        }

        //fom a key based on remainingWeight and remainingCount
        Index key = new Index();
        key.remainingItems = totalItems - currentItem -1;
        key.remainingWeight = remainingWeight;

        //see if key exists in map. If so then return the maximumValue for key stored in map.
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int maxValue;
        //if weight of item is more than remainingWeight then try next item by skipping current item
        if(remainingWeight < weights[currentItem]) {
            maxValue = topDownRecursiveUtil(values, weights, remainingWeight, totalItems, currentItem + 1, map);
        } else {
            //try to get maximumValue of either by picking the currentItem or not picking currentItem
            maxValue = Math.max(values[currentItem] + topDownRecursiveUtil(values, weights, remainingWeight - weights[currentItem], totalItems, currentItem + 1, map),
                    topDownRecursiveUtil(values, weights, remainingWeight, totalItems, currentItem + 1, map));
        }
        //memoize the key with maxValue found to avoid recalculation
        map.put(key, maxValue);
        return maxValue;

    }

    public static void main(String args[]){

        Knapsack01 k = new Knapsack01();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int r = k.bottomUpDP(val, wt, 30);
        int r1 = k.topDownRecursive(val, wt, 30);

        int r3 = k.bottomUpDP2(val, wt, 30);
        System.out.println(r);
        System.out.println(r1);
    }

}
