/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [2,3]
 */

public class Solution {
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		// sol 1: use HashSet, O(n) Time, O(n) Space.
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (!set.add(num)) {
				res.add(num);
			}
		}
		return res;

		// sol 2: Since nums in the array are positive, we can use negative numbers for marking
		// For a number i, flip nums[i-1] to negative, if it's already negative, i occurs twice.
		// O(n) Time, no extra space.
		for (int num : nums) {
			int index = Math.abs(num) - 1;
			if (nums[index] < 0) {	// already occurred before
				res.add(Math.abs(index + 1));
			}
			nums[index] = - nums[index];
		}
		return res;
	}
}