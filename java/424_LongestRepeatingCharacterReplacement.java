/**
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 * Note:
 * Both the string's length and k will not exceed 104.
 * Example 1:
 * Input:
 * s = "ABAB", k = 2
 * Output:
 * 4
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * Input:
 * s = "AABABBA", k = 1
 * Output:
 * 4
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */

public class Solution {
	public int characterReplacement(String s, int k) {
		// idea: sliding window, move the right pointer and track the freq of the majority letter in the
		// window. When there are more than k non-majority characters in the window, move the left pointer.
		// Update the maxCount (count of majority) and maxLen during the sliding.	O(n) Time, O(1) Space.
		int[] count = new int[26];	// since only contains uppercase English letters
		int maxCount = 0, maxLen = 0;
		int left = 0, right = 0;
		while (right < s.length()) {
			maxCount = Math.max(maxCount, ++count[s.charAt(right) - 'A']);
			while (right - left + 1 - maxCount > k) {	// more than k non-majority characters
				count[s.charAt(left) - 'A']--;
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);
			right++;
		}
		return maxLen;
	}
}