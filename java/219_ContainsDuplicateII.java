/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
 */

public class Solution {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		// idea: use a HashSet but only store the numbers within a window of size k
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k) {	// the set stores nums[i - k] to nums[i]
				set.remove(nums[i - k - 1]);
			}
			if (!set.add(nums[i])) {
				return true;
			}
		}
		return false;
	}
}