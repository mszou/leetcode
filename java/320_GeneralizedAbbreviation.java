/**
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

public class Solution {
	// sol 1: DFS + backtracking. for each character, we can choose whether to keep it or abbreviate it.
	// in dfs, we need a variable to count # consecutive abbreviated bits before the current position.
	public List<String> generateAbbreviations(String word) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		char[] wordArray = word.toCharArray();
		dfs(res, sb, wordArray, 0, 0);
		return res;
	}

	private dfs(List<String> res, StringBuilder sb, char[] wordArray, int pos, int numCount) {
		if (pos >= wordArray.length) {
			if (numCount > 0) {
				sb.append(numCount);
			}
			res.add(sb.toString());
			return;
		}
		// use number at this position
		int len = sb.length();
		dfs(res, sb, wordArray, pos + 1, numCount + 1);
		sb.setLength(len);	// backtracking
		// use character at this position
		len = sb.length();
		if (numCount > 0) {
			sb.append(numCount).append(wordArray[pos]);
			dfs(res, sb, wordArray, pos + 1, 0);
		} else {
			sb.append(wordArray[pos]);
			dfs(res, sb, wordArray, pos + 1, 0);
		}
		sb.setLength(len);	// backtracking
	}

	// sol 2: iterative. A word of length k has 2^k abbreviations. Moreover, they can be mapped to
	// all the binary expressions of length k (i.e. 0 ~ 2^k-1). A '0' at position i in the binary
	// number means we keep the character at that position, and '1' means we abbreviate it. If there
	// are consecutive 1's, we need to count the number of 1's and replace them with the count.
	public List<String> generateAbbreviations(String word) {
		List<String> res = new ArrayList<>();
		int n = word.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.pow(2, n); i++) {
			sb.setLength(0);
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 1) != 0) {
					count++;
				} else {
					if (count != 0) {
						sb.append(count);
						count = 0;
					}
					sb.append(word.charAt(j));
				}
			}
			if (count > 0) {
				sb.append(count);
			}
			res.add(sb.toString());
		}
		return res;
	}
}