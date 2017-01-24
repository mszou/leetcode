/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [5,6]
 */

public class Solution {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		// idea: since the given numbers are all in [1, n], we can use negative number to
		// mark indices for appeared number, i.e. let nums[nums[i] -1] = -nums[nums[i]-1]
		// In this way, we can both know the original numbers and mark the appeared
		// Subtracting 1 is to map all integers from 1 to n using the current array index
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				res.add(i + 1);
			}
		}
		return res;
	}
}