package DP;
/* 九章 强化 DP
https://leetcode.com/problems/house-robber/
https://leetcode.com/problems/climbing-stairs/
https://leetcode.com/problems/maximal-square/   todo
https://leetcode.com/problems/longest-continuous-increasing-subsequence/
Longest Increasing Continuous subsequence II   todo https://www.jianshu.com/p/1587a3385dfe
Coins in a Line
Coins in a Line todo II https://pobenliu.gitbooks.io/leetcode/content/Coins%20in%20a%20Line%20II.html

 */
public class 九章强化_DP {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(nums[i-1] + dp[i-2],
                    dp[i-1]);
        }
        return dp[nums.length];
    }
}
