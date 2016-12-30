/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * UPDATE (2016/2/13):
 * The return format had been changed to zero-based indices. Please read the above updated description carefully.
 */

public class Solution {
	public int[] twoSum(int[] nums, int target) {
		// idea: if array is sorted, can use two pointers from left and right
		// if not sorted or data stream, use HashMap and search for difference
		int[] res = new int[2];
		if (nums == null || nums.length < 2) {
			return res;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int difference = target - nums[i];
			if (map.containsKey(difference)) {
				res[0] = map.get(difference);
				res[1] = i;
				return res;
			} else {
				map.put(nums[i], i);
			}
		}
	}
}