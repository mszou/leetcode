/*
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */

public class Solution {
	// idea: DFS + backtracking, skip duplicate permutations
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		Arrays.sort(nums);
		List<Integer> list = new ArrayList<Integer>();
		dfs(res, list, nums, 0);
		return res;
	}

	private void dfs(List<List<Integer>> res, List<Integer> subset, int[] nums, int start) {
		res.add(new ArrayList<Integer>(subset));	// add the current subset first
		for (int i = start; i < nums.length; i++) {
			// must add nums[start] to make sure subset containing duplicate nums can be added once
			if (i != start && nums[i] == nums[i - 1]) {	// avoid duplicate subset
				continue;
			}
			subset.add(nums[i]);
			dfs(res, subset, nums, i + 1);
			subset.remove(subset.size() - 1);
		}
	}
}