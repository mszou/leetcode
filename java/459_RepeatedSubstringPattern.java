/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 * Input: "aba"
 * Output: False
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

public class Solution {
	public boolean repeatedSubstringPattern(String str) {
		// idea: if the string can be constructed by this method, first its length must be a multiple
		// of the length of pattern, so check the substrings whose length is a divisor of str.length()
		// there are at most sqrt(n)*2 = O(sqrt(n)) divisors, so Worst O(n^1.5) Time, Best O(n) Time
		int len = str.length();
		for (int i = len / 2; i >= 1; i--) {	// at least repeat twice
			if (len % i == 0) {	// i is a divisor of len
				int times = len / i;
				String pattern = str.substring(0, i);
				int j = i;
				for (; j < len; j += i) {
					if (!pattern.equals(str.substring(j, j + i))) {
						break;	// not match the 'pattern'
					}
				}
				if (j == len) {
					return true;
				}
			}
		}
		return false;
	}
}