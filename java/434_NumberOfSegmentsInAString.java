/**
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 * Please note that the string does not contain any non-printable characters.
 * Example:
 * Input: "Hello, my name is John"
 * Output: 5
 */

public class Solution {
	public int countSegments(String s) {
		// sol 1: using trim() and split()
		String trimmed = s.trim();
		if (trimmed.length() == 0) {
			return 0;
		} else {
			return trimmed.split("\\s+").length;
		}

		// sol 2: check characters, count the first character of each segment
		int seg = 0;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				seg++;
			}
			while (i < chars.length && chars[i] != ' ') {
				i++;
			}
		}
		return seg;
	}
}