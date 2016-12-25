/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 */

public class Solution {
	// idea: 2-round traverse, first from left to right and remove invalid ')', then reverse to remove
	// invalid '('. need to keep tracking the last removal position to avoid making duplicate results
	// When remove one ')' from a series of consecutive ')'s, only consider removing the first one
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();
		remove(s, res, 0, 0, new char[]{'(', ')'});
		return res;
	}

	private void remove(String s, List<String> res, int last_i, int last_j, char[] paren) {
		int diff = 0;	// difference between number of '(' & number of ')'
		// i is the pointer to take in next character, j is the pointer to find character to remove
		for (int i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == paren[0]) {
				diff++;
			}
			if (s.charAt(i) == paren[1]) {
				diff--;
			}
			if (diff >= 0) {
				continue;
			}
			for (int j = last_j; j <= i; j++) {
				// only look for invalid ')' in first round, and invalid '(' in second round
				// only consider characters after the position of last removal, i.e. last_j
				// for concecutive parentheses, only consider removing the first one to avoid duplicate
				if (s.charAt(j) == paren[1] && (j == last_j || s.charAt(j - 1) != paren[1])) {
					// remove the parenthesis at position j, recursively analyze new string with updated i,j
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, paren);
				}
			}
			return;
		}
		// after first round, there is no invalid ')', then reverse the string to check '('s
		String reversed = new StringBuilder(s).reverse().toString();
		if (paren[0] == '(') {	// means first round (left -> right) finished
			remove(reversed, res, 0, 0, new char[]{')','('});
		} else {	// means second round (right -> left) finished
			res.add(reversed);	// now 'reversed' is the result in correct order
		}
	}
}