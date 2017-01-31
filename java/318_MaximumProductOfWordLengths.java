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
		// idea: bit manipulation. An int has 32 bits, which is enough for 26 letters. So we can convert each
		// word into a binary number, where a 1 at i-th bit means the word contains letter ('a'+i). Thus we can
		// easily check whether two words have common letters by & operation.	O(max{nm, n^2}) Time, O(n) Space.
		if (words == null || words.length < 2) {
			return 0;
		}
		int len = words.length;
		int[] value = new int[len];	// value[i] the corresponding value for words[i]
		// O(nm) Time for converting words into binary numbers
		for (int i = 0; i < len; i++) {
			String curr = words[i];
			for (char c : curr.toCharArray()) {
				value[i] |= 1 << (c - 'a');	// let value[i] have a 1 at (c-'a')-th bit
			}
		}
		int maxProduct = 0;
		// O(n^2) Time for checking products
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