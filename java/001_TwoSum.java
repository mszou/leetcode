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
		// idea: if array is sorted, use two pointers from left & right; if not sorted
		// or is data stream, use HashMap and search for difference (target - curr)
		// two pointer: O(n) Time, O(1) Space; HashMap: O(n) Time, O(n) Space
		int[] res = new int[2];
		if (nums == null || nums.length < 2) {
			return res;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int diff = target - nums[i];
			if (map.containsKey(diff)) {
				res[0] = map.get(diff);	// this num occurs earlier than nums[i]
				res[1] = i;
				return res;
			} else {
				map.put(nums[i], i);
			}
		}
	}
}