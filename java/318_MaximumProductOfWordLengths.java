/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.
 */

public class Solution {
	public int maxProduct(String[] words) {
		// idea: convert each word into a binary number, if the number has a 1 at i-th bit, it means the word
		// contains letter ('a'+i). thus can easily check whether two words have common letters by bit manipulation
		if (words == null || words.length < 2) {
			return 0;
		}
		int len = words.length;
		int[] value = new int[len];	// to record the corresponding values for words
		for (int i = 0; i < len; i++) {
			String cur = words[i];
			for (char c : cur.toCharArray()) {
				value[i] |= 1 << (c - 'a');
			}
		}
		int maxProduct = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if ((value[i] & value[j]) == 0) {	// don't share common letters
					maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
				}
			}
		}
		return maxProduct;
	}
}