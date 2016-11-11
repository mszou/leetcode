/*
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */

public class Solution {
	// idea: DFS + backtracking
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (k == 0 || k > n) {
			return res;
		}
		helper(res, new ArrayList<Integer>(), 1, n, k);
		return res;
	}

	// choose (rest) numbers from start to n to form a comination
	private void helper(List<List<Integer>> res, List<Integer> comb, int start, int n, int rest) {
		if (rest == 0) {
			res.add(new ArrayList<Integer>(comb));
			return;
		}
		for (int i = start; i <= n; i++) {
			comb.add(i);
			helper(res, comb, i + 1, n, rest - 1);
			comb.remove(comb.size() - 1);
		}
	}
}