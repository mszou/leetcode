/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 */

public class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// idea: sliding window, use an array or a HashMap to record the freq of each char.
		// count the distinct char within the window (substring). if the count exceeds k,
		// remove characters from the left side until one char has 0 freq (count becomes k).
		if (s == null || s.length() == 0 || k <= 0) {
			return 0;
		}
		if (s.length() <= k) {
			return s.length();
		}

		// version 1: use count array. O(n) Time, Constant Space
		int[] freq = new int[256];	// size of ASCII characters
		int count = 0, left = 0, maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			if (count[s.charAt(i)]++ == 0) {
				count++;
			}
			if (count > k) {
				while (--count[s.charAt(left)] > 0) {
					left++;
				}
				count--;
			}
			maxLen = Math.max(maxLen, i - left + 1);
		}
		return maxLen;

		// version 2: use HashMap<character, frequency>. O(n) Time, O(k) Space
		Map<Character, Integer> map = new HashMap<>();
		int left = 0, maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
			while (map.size() > k) {
				char leftChar = s.charAt(left);
				map.put(leftChar, map.get(leftChar) - 1);
				if (map.get(leftChar) == 0) {
					map.remove(leftChar);
				}
				left++;
			}
			maxLen = Math.max(maxLen, i - left + 1);
		}
		return maxLen;
	}
}