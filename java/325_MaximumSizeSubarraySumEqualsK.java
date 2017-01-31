/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * Follow Up:
 * Can you do it in O(n) time?
 */

public class Solution {
	// idea: use Map<sum, index> to store the accumulative sum from beginning to each position.
	// the sum of subarray can be expressed as the difference of two sums. Traverse the array,
	// if there is a previousSum == currentSum - k, then find an eligible subarray, update max.
	// if the map has already contained the currentSum, don't put new index because subtracting
	// smaller index can lead to a longer subarray.		O(n) Time, O(n) Space.
	public int maxSubArrayLen(int[] nums, int k) {
		int sum = 0, max = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) {
				max = Math.max(max, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return max;
	}
}