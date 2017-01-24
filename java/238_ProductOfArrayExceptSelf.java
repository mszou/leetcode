/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class Solution {
	public int[] productExceptSelf(int[] nums) {
		// idea: since division not allowed, res[i] = product of nums[0,..,i-1] * nums[i+1,..,n-1], so
		// traverse from left to right and set res[i] as product of nums[0,..,i-1], then traverse from
		// right to left and update res[i] by multiplying the product on the right.	O(n) Time, O(1) Space.
		int n = nums.length;
		int[] res = new int[n];
		// Calculate left products from left end and store in res
		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}
		// Calculate right products from the right end and update res
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}
}