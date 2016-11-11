/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */

public class Solution {
	// backtracking. the exchange of identical numbers will not form a new permutation, so only count
	// one order for duplicates as valid, here is from smaller index to larger index
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		List<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);	// use a map<num, count> to record duplicates? Maybe more complicated to deal with order
		Arrays.fill(visited, false);
		helper(res, list, visited, nums);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, boolean[] visited, int[] nums) {
		if (list.size() == nums.length) {	// a permutation formed
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {	// !visited[i - 1] means duplicate with smaller index has not been used
				continue;
			}
			visited[i] = true;
			list.add(nums[i]);
			helper(res, list, visited, nums);
			list.remove(list.size() - 1);	// backtracking
			visited[i] = false;
		}
	}
}