/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * Note: 
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * Given [3, 1, 5, 8]
 * Return 167
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

public class Solution {
    public int maxCoins(int[] nums) {
        // idea: Divide & conquer and DP, dp[i][j] is the max coins from ith to jth balloon,
        // considering the last balloon to burst, and divide into left, right & middle parts.
        // recursive Divide & Conquer method with memoization & DP. O(n^3) Time
        int n = nums.length;
        int[] arr = new int[n + 2]; // current array to burst
        int[][] dp = new int[n + 2][n + 2];
        boolean[][] visit = new boolean[n + 2][n + 2];
        // initialize the array, arr[i] stands for the i-th balloon
        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }
        arr[0] = arr[n + 1] = 1;    // set the balloon before first / after last as 1
        return burst(arr, dp, visit, 1 , n);
    }
    
    // function to calculate the max coins for arr[left] ~ arr[right]
    private int burst(int[] arr, int[][] dp, boolean[][] visit, int left, int right) {
        if (visit[left][right]) {   // already computed [left, right] this range
            return dp[left][right];
        }
        int res = 0;
        // for each balloon in this segment, compute the coins we can get by last bursting it
        for (int i = left; i <= right; i++) {
            // i is the last to burst between [left, right], so multiply with left-1 & right+1
            int midValue =  arr[left - 1] * arr[i] * arr[right + 1];
            int leftValue = burst(arr, dp, visit, left, i - 1);
            int rightValue = burst(arr, dp, visit, i + 1, right);
            res = Math.max(res, leftValue + midValue + rightValue); // choose i as last or not
        }
        visit[left][right] = true;
        dp[left][right] = res;
        return res;
    }
}