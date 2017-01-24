/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

public class Solution {
	public boolean wordBreak(String s, List<String> wordDict) {
		// idea: DP. canBreak[i] is whether s.substring(0, i) can be segmented
		// if s.length() = n, wordDict.size() = m, then O(mn) Time, O(n) Space.
		boolean[] canBreak = new boolean[s.length() + 1];
		canBreak[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < wordDict.size(); j++) {
				String word = wordDict.get(j);
				if (word.length() <= i) {
					if (canBreak[i - word.length()] && s.substring(i - word.length(), i).equals(word)) {
						canBreak[i] = true;
						break;
					}
				}
			}
		}
		return canBreak[s.length()];
	}
}