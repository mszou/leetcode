/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */

public class Solution {
	// idea: DFS + backtracking, try every possible way to partition into palindromic substrings
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		List<String> list = new ArrayList<String>();
		dfs(res, list, s, 0);
		return res;
	}

	private void dfs(List<List<String>> res, List<String> list, String s, int start) {
		if (start == s.length() && list.size() > 0) {
			res.add(new ArrayList<String>(list));
		} else {
			for (int i = start; i < s.length(); i++) {
				if (isPalin(s, start, i)) {	// s.substring(start, i + 1) is palindromic
					list.add(s.substring(start, i + 1));
					dfs(res, list, s, i + 1);
					list.remove(list.size() - 1); 	// backtracking
				}
			}
		}
	}

	private boolean isPalin(String s, int start, int end) {	// O(n) to check palindrome
		while (start < end) {
			if (s.charAt(start++) != s.charAt(end--)) {
				return false;
			}
		}
		return true;
	}
}