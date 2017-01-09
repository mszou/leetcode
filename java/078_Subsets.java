/*
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

public class Solution {
	// sol 1: DFS + backtracking (recursion), O(2^n) Time.
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		// Arrays.sort(nums);
		List<Integer> list = new ArrayList<Integer>();
		dfs(res, list, nums, 0);
		return res;
	}

	private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
		res.add(new ArrayList<Integer>(list));	// first add the current list to result
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			dfs(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
		return;
	}

	// sol 2: bit manipulation. there are 2^n subsets for array of length n. If we use 1 and 0
	// to express whether to take this num in subset or not, all the subsets can be expressed
	// by all binary expressions of length n from 0..0 to 1..1, corresponding to 0 ~ 2^n - 1.
	// So we simply check: if k & (1<<i) != 0, then the (0-based) k-th subset contains nums[i]
	public List<List<Integer>> subsets(int[] nums) {
		int totalNumber = 1 << nums.length;	// 2^n subsets total
		List<List<Integer>> res = new ArrayList<>();
		// Arrays.sort(nums);
		for (int i = 0; i < totalNumber; i++) {
			List<Integer> subset = new ArrayList<Integer>();
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) != 0) {
					subset.add(nums[j]);
				}
			}
			res.add(subset);
		}
		return res;
	}
}