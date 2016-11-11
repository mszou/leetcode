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
	// idea: DFS + backtracking (recursion)
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		// Arrays.sort(nums);
		List<Integer> list = new ArrayList<Integer>();
		helper(res, list, nums, 0);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
		res.add(new ArrayList<Integer>(list));
		for (int i = start; i < nums.length; i++) {
			list.add(nums[i]);
			helper(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
		return;
	}
}