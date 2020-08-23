package JumpGames;

import java.util.Arrays;

/*
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MinJumpToReachEnd.java
Given an array of non negative integers where each element represents the max
 * number of steps that can be made forward from that element. Write a function to
 * return the minimum number of jumps to reach the end of the array from first element
index   0   1   2   3   4   5   6   7   8   9   10
        1   3   5   3   2   2   6   1   6   8   9

dp[end] = min
jumps   0   1   2   2   2
0   1


 */
public class MinJumpToReachEnd {
    public int minJump(int[] arr, int[] from) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int start = 0; start < arr.length; start++) {
            for (int end = 1; end < arr.length; end++) {


                if (start + arr[start] >= end
                    && dp[start] + 1 < dp[end]) {
                    if (end == 11) {
                        System.out.println();
                    }
                    dp[end] = dp[start] + 1;
                    from[end] = start;
                }
            }
        }

        return dp[arr.length - 1];
    }

    public int minimumJump_greedy(int[] nums) {
        int jump = 0;
        //        1   3   5   3   2   2   6   1   6   8   9
        int furthest = 0;
        int rangeEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            furthest = Math.max(furthest, i + nums[i] );

            if (i == rangeEnd) {
                jump++;
                rangeEnd = furthest;

                if (furthest >= nums.length - 1) {
                    return jump;
                }
            }

        }

        return -1;

    }

    public static void main(String args[]){
        MinJumpToReachEnd mj = new MinJumpToReachEnd();
        int arr[] = {1,3,5,3,2,2,6,1,6,8,9};
        int r[] = new int[arr.length];
        int result = mj.minJump(arr,r);
        System.out.println(result);
        int i = arr.length-1;
        Arrays.toString(r);
        int arr1[] = {2,3,1,1,4};
      //  System.out.print(mj.jump(arr));
    }
}
