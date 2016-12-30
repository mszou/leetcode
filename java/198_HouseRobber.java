/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {
    public int rob(int[] nums) {
        // idea: DP. consider each value in 2 cases: rob or not rob. O(n) Time, O(1) Space
        // use two variables to store the max money can get so far if rob / not rob this house
        int prevRob = 0, prevNotRob = 0;
        // traverse all the nums, and update these two cases
        for (int n : nums) {
        	int currRob = prevNotRob + n;  // if rob curr, prev cannot be robbed
            int currNotRob = Math.max(prevNotRob, prevRob); // if not rob curr, take the max
            // update values for next round
            prevRob = currRob;
            prevNotRob = currNotRob;
        }
        return Math.max(prevRob, prevNotRob);
    }
}