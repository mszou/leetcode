/**
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

public class Solution {
	public String decodeString(String s) {
		// idea: use Stack + StringBuilder
		Stack<Integer> intStack = new Stack<Integer>();
		Stack<StringBuilder> strStack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int num = 0;
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				num = num * 10 + c - '0';
			} else if (c == '[') {
				intStack.push(num);
				strStack.push(sb);
				sb = new StringBuilder();
				num = 0;
			} else if (c == ']') {
				String temp = sb.toString();	// temp is the string needing to be repeated
				sb = strStack.pop();
				for (int i = intStack.pop(); i > 0; i--) {
					sb.append(temp);
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}