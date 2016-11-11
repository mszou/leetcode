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
	// idea: DFS + backtracking, check whether each substring is palindromic
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		List<String> list = new ArrayList<String>();
		helper(res, list, s, 0);
		return res;
	}

	private void helper(List<List<String>> res, List<String> list, String s, int start) {
		if (start == s.length() && list.size() > 0) {
			res.add(new ArrayList<String>(list));
		} else {
			for (int i = start; i < s.length(); i++) {
				if (isPal(s, start, i)) {	// s.substring(start, i + 1) is palindromic
					list.add(s.substring(start, i + 1));
					helper(res, list, s, i + 1);
					list.remove(list.size() - 1); 	// backtracking
				}
			}
		}
	}

	private boolean isPal(String s, int start, int end) {
		while (start < end) {
			if (s.charAt(start++) != s.charAt(end--)) {
				return false;
			}
		}
		return true;
	}
}