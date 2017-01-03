/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */

public class Solution {
	public int firstUniqChar(String s) {
		// idea: count the frequency for each char in s then traverse to find the first with freq = 1
		int count[] = new int[256];	// 256 is the size of ASCII characters
		for (char c : s.toCharArray()) {
			count[c]++;
		}
		for (int i = 0; i < s.length(); i++) {
			if (count[s.charAt(i)] == 1) {
				return i;
			}
		}
		return -1;
	}
}