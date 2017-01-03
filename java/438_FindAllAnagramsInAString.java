/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

public class Solution {
	public List<Integer> findAnagrams(String s, String p) {
		// idea: sliding window of length p.length(), use 2 pointers left & right to determine the window
		// record the frequency of each character in p as reference when traversing s
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0) {
			return res;
		}
		int[] freq = new int[256];	// character hash, 256 is the size of ASCII characters
		// record the count of each character in p
		for (char c : p.toCharArray()) {
			freq[c]++;
		}
		int left = 0, right = 0;	// two pointers representing the window
		int count = p.length();	// number of characters that still need to be matched
		while (right < s.length()) {
			if (freq[s.charAt(right++)]-- >= 1) {
				count--;	// find a matched character
			}
			if (count == 0) {	// matched all chars in p, i.e. find an anagram
				res.add(left);	// add the start index
			}
			if (right - left == p.length()) {	// need to move the left pointer
				if (freq[s.charAt(left++)]++ >= 0) {	// means the character being kicked out was matched in p
					count++;
				}
			}
		}
		return res;
	}
}