/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

public class Solution {
	public boolean isAnagram(String s, String t) {
		// idea: count the occurance of each letter in s & t, and compare
		int len1 = s.length(), len2 = t.length();
		if (len1 != len2) {
			return false;
		}
		int[] counts = new int[26];
		for (char i : s.toCharArray()) {
			counts[i - 'a']++;
		}
		for (char j : t.toCharArray()) {
			if (--counts[j - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}
}