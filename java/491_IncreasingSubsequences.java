/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */

public class Solution {
	// idea: DFS + backtracking, use a set to avoid duplicate res.
	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		List<Integer> holder = new ArrayList<Integer>();
		findSubsequences(set, holder, nums, 0);
		List res = new ArrayList(set);
		return res;
	}

	private void findSubsequences(Set<List<Integer>> set, List<Integer> holder, int[] nums, int index) {
		if (holder.size() >= 2) {
			set.add(new ArrayList(holder));
		}
		for (int i = index; i < nums.length; i++) {
			if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
				holder.add(nums[i]);
				findSubsequences(set, holder, nums, i + 1);
				holder.remove(holder.size() - 1);	// backtracking
			}
		}
	}
}