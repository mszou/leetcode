/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * word1 and word2 may be the same and they represent two individual words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * Note:
 * You may assume word1 and word2 are both in the list.
 */

public class Solution {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		// version 1: in every round, use a temp variable to record last index for word1.
		int pos1 = -1, pos2 = -1;
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			int t = pos1;
			if (words[i].equals(word1)) {
				pos1 = i;
			}
			if (words[i].equals(word2)) {
				pos2 = i;
			}
			if (pos1 != -1 && pos2 != -1) {
				if (word1.equals(word2) && t != -1 && t != pos1) {	// means pos1 & pos2 updated in this round
					res = Math.min(res, Math.abs(t - pos1));
				} else if (pos1 != pos2) {
					res = Math.min(res, Math.abs(pos1 - pos2));
				}
			}
		}
		return res;

		// version 2: shorter, if two words are same, pos1 can get the last index from pos2
		long res = Integer.MAX_VALUE, pos1 = res, pos2 = -res;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				pos1 = i;
			}
			if (words[i].equals(word2)) {
				if (word1.equals(word2)) {
					pos1 = pos2;	// here pos1 will be the last index
				}
				pos2 = i;
			}
			res = Math.min(res, Math.abs(pos1 - pos2));
		}
		return (int)res;

		// version 3: use only one variable to record the last position that hits
		int idx = -1, res = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1) || words[i].equals(word2)) {
				if (idx != -1 && (word1.equals(word2) || !words[i].equals(words[idx]))) {	// 2 cases for same words & different words
					res = Math.min(res, i - idx);
				}
				idx = i;
			}
		}
	}
}