/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */

public class Solution {
	public boolean canJump(int[] nums) {
		// idea: Greedy. traverse from left to right and keep tracking the farthest position can reach
		if (nums == null || nums.length == 0) {
			return false;
		}
		int farthest = nums[0];	// records the farthest position that can be reached so far
		for (int i = 0; i < nums.length; i++) {
			if (i <= farthest && nums[i] + i > farthest) {
				farthest = nums[i] + i;
			}
			if (farthest >= nums.length - 1) {
				return true;
			}
		}
		return false;
	}
}