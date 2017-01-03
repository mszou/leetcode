/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * Example:
 * Input:
 * "abccccdd"
 * Output:
 * 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

public class Solution {
	public int lonestPalindrome(String s) {
		// idea: count each character, use HashSet(simplest), HashMap, or array
		if (s == null || s.length() == 0) {
			return 0;
		}
		int count = 0;	// count the number of pairs
		HashSet<Character> set = new HashSet<Character>();	// store chars with odd freq
		for (char c : s.toCharArray()) {
			if (!set.add(c)) {	// already exist
				set.remove(c);
				count++;
			}
		}
		if (!set.isEmpty()) {
			return count * 2 + 1;
		} else {
			return count * 2;
		}
	}
}