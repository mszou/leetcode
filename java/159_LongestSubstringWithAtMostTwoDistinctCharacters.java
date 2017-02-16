/**
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 * For example,
 * Given S = “eceba”,
 * T is “ece” which its length is 3.
 */

public class Solution {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		// idea: maintain a sliding window with no more than 2 unique characters. use map
		// to store the freq of each character within the window.	O(n) Time, O(n) Space.
		if (s.length() < 1) {
			return 0;
		}
		if (s.length() <= 2) {
			return s;
		}
		Map<Character, Integer> map = new HashMap<>();
		int left = 0, maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
			while (map.size() > 2) {
				char leftChar = s.charAt(left);
				map.put(leftChar, map.get(leftChar) - 1);
				if (map.get(leftChar) == 0) {
					map.remove(leftChar);
				}
				left++;
			}
			maxLen = Math.max(maxLen, i - left + 1);
		}
		return maxLen
	}
}