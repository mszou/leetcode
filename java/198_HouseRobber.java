/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {
    public int rob(int[] nums) {
        // idea: DP. consider each value in 2 cases: rob or not rob. O(n) Time, O(1) Space
        int prevYes = 0;	// max money can get if rob current house
        int prevNo = 0;		// max money can get if not rob current house
        // traverse all the nums, and update these two cases
        for (int n : nums) {
        	int temp = prevNo;	// if rob current house, the prev cannot be robbed
        	prevNo = Math.max(prevNo, prevYes);	// not rob current house, max is the max at prev
        	prevYes = n + temp;	// rob current house, add the money to what you already got
        }
        return Math.max(prevNo, prevYes);
    }
}