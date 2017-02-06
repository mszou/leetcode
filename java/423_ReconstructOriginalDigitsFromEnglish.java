/**
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 * Example 1:
 * Input: "owoztneoer"
 * Output: "012"
 * Example 2:
 * Input: "fviefuro"
 * Output: "45"
 */

public class Solution {
	public String originalDigits(String s) {
		// idea: zero is the only word that has letter 'z', one is the only word that has 'w',...
		// Since the input is guaranteed to be valid, we can only count the unique letter of each word.
		// For the words that have no unique letter, we choose a letter and subtract # other words that
		// share this letter. e.g. we choose z for 0, w for 2, u for 4, x for 6, g for 8, o for 1 (need
		// to subtract 0,2,4), h for 3 (-8), f for 5 (-4), s for 7 (-6), i for 9(-5,6,8).	O(n) Time.
		int[] count = new int[10];
		for (char c : s.toCharArray()) {
			if (c == 'z') count[0]++;
			if (c == 'w') count[2]++;
			if (c == 'u') count[4]++;
			if (c == 'x') count[6]++;
			if (c == 'g') count[8]++;
			if (c == 'o') count[1]++;	// 1 - 0 - 2 - 4
			if (c == 'h') count[3]++;	// 3 - 8
			if (c == 'f') count[5]++;	// 5 - 4
			if (c == 's') count[7]++;	// 7 - 6
			if (c == 'i') count[9]++;	// 9 - 5 - 6 - 8
		}
		count[3] -= count[8];
		count[5] -= count[4];
		count[7] -= count[6];
		count[1] = count[1] - count[0] - count[2] - count[4];
		count[9] = count[9] - count[5] - count[6] - count[8];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < count[i]; j++) {
				sb.append(i);
			}
		}
		return sb.toString();
	}
}