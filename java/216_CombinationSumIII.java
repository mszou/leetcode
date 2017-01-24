/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 */

public class Solution {
	// idea: DFS + backtracking, for 1~9, add one number, dfs, then remove and backtracking, for each combination
	// only count the small -> large permutation.
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (k > 9 || n < k || n > 9 * k) {	// because no duplicate nums allowed in combination
			return res;
		}
		combination(res, new ArrayList<Integer>(), k, 1, n);
		return res;
	}

	// DFS to find k numbers from 'start' to form a sum target based on the current list
	private void combination(List<List<Integer>> res, List<Integer> list, int k, int start, int target) {
		if (list.size() == k && target == 0) {	// find a combination
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for (int i = start; i <= 9; i++) {	// try from start to 9
			if (i > target) {
				break;
			}
			list.add(i);
			combination(res, list, k, i + 1, target - i);	// start becomes i+1 because of requirement for uniqueness
			list.remove(list.size() - 1);
		}
	}
}