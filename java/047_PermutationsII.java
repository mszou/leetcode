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
	// idea: backtracking. the exchange of identical numbers will not form a new permutation,
	// so only count one order for duplicates as valid, here is from smaller index first.
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		List<Integer> list = new ArrayList<Integer>();
		boolean[] used = new boolean[nums.length];	// record whether num has been used in current list
		Arrays.sort(nums);	// use a map<num, count> to record duplicates? Maybe more complicated to deal with order
		Arrays.fill(used, false);
		helper(res, list, used, nums);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, boolean[] used, int[] nums) {
		if (list.size() == nums.length) {	// a permutation formed
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {	// !used[i - 1] means duplicate with smaller index has not been used
				continue;
			}
			used[i] = true;
			list.add(nums[i]);
			helper(res, list, used, nums);
			list.remove(list.size() - 1);	// backtracking
			used[i] = false;
		}
	}
}