/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * Example 1:
 * Input:
 * s = "aaabb", k = 3
 * Output:
 * 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * Input:
 * s = "ababbc", k = 2
 * Output:
 * 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

public class Solution {
	// idea: divide and conquer + recursive. For a string or substring, count the freq of 26 letters.
	// if there is a letter whose freq < k, we split the (sub)string from the pos of one of this letter
	// and find the longest eligible substring in left part and right part separately.	O(n^2) Time.
	public int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		return helper(str, 0, s.length(), k);
	}

	private int helper(char[] str, int start, int end, int k) {
		if (end - start < k) {
			return 0;	// the length of substring(start, end) is smaller than k
		}
		int[] count = new int[26];
		for (int i = start; i < end; i++) {	// count the freq of every letter in the substring
			count[str[i] - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] < k && count[i] > 0) {
				for (int j = start; j < end; j++) {
					if (str[j] == i + 'a') {
						int left = helper(str, start, j, k);
						int right = helper(str, j + 1, end, k);
						return Math.max(left, right);
					}
				}
			}
		}	// reach here means no letter whose freq < k in substring(start, end)
		return end - start;
	}
}