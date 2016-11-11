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
		List<Integer> list = new ArrayList<Integer>();
		Arrays.sort(nums);
		helper(res, list, nums, 0);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
		res.add(new ArrayList<Integer>(list));
		for (int i = start; i < nums.length; i++) {
			if (i != start && nums[i] == nums[i - 1]) {	// duplicates
				continue;
			}
			list.add(nums[i]);
			helper(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}
}