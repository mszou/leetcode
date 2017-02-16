/**
 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * Example 1:
 * str = "aabbcc", k = 3
 * Result: "abcabc"
 * The same letters are at least distance 3 from each other.
 * Example 2:
 * str = "aaabc", k = 3 
 * Answer: ""
 * It is not possible to rearrange the string.
 * Example 3:
 * str = "aaadbbcc", k = 2
 * Answer: "abacabcd"
 * Another possible answer is: "abcabcda"
 * The same letters are at least distance 2 from each other.
 */

public class Solution {
	// idea: greedy, every time we want to find the best candidate: the char with the largest remaining count.
	// count[i] is the remaining count for char i, and valid[i] is the most left index that char i can appear.
	// O(n) Time, O(1) Space (count & valid are of length 26).
	public String rearrangeString(String str, int k) {
		int length = str.length();
		int[] count = new int[26];
		int[] valid = new int[26];
		for (char c : str.toCharArray()) {
			count[c - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int candidate = findValidMax(count, valid, i);
			if (candidate == -1) {
				return "";
			}
			count[candidate]--;
			valid[candidate] = i + k;
			sb.append((char)('a' + candidate));
		}
		return sb.toString();
	}

	private int findValidMax(int[] count, int[] valid, int index) {
		int max = Integer.MIN_VALUE;
		int candidate = 01;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0 && count[i] > max && index >= valid[i]) {
				max = count[i];
				candidate = i;
			}
		}
		return candidate;
	}
}