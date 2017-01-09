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
	// idea: DFS + backtracking.	O(n^min{k,n-k}) Time.
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (k == 0 || k > n) {
			return res;
		}
		dfs(res, new ArrayList<Integer>(), 1, n, k);
		return res;
	}

	// choose k numbers from start to end to form a comination with comb
	private void dfs(List<List<Integer>> res, List<Integer> comb, int start, int end, int k) {
		if (k == 0) {
			res.add(new ArrayList<Integer>(comb));
			return;
		}
		for (int i = start; i <= end; i++) {
			comb.add(i);
			dfs(res, comb, i + 1, end, k - 1);
			comb.remove(comb.size() - 1);
		}
	}
}