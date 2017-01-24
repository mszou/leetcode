/**
 * Note: This is an extension of House Robber.
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {
    public int rob(int[] nums) {
        // idea: houses in a circle, so first & last are adjacent. Since they cannot be both robbed, we choose the larger result
        // between not robbing the first one and not robbing the last one, break the circle into a line.    O(n) Time, O(1) Space.
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        if (nums.length == 1) {
        	return nums[0];
        }
        // choose from the money we can get in 2 cases: not rob last house or not rob first house
        return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
    }

    private int helper(int[] nums, int start, int end) {
    	int rob = 0, notrob = 0;   // the max money can get if rob this house or not
    	for (int i = start; i <= end; i++) {
    		int preRob = rob, preNot = notrob;    // previous rob or not
    		rob = preNot + nums[i];
    		notrob = Math.max(preRob, preNot);
    	}
    	return Math.max(rob, notrob);
    }
}