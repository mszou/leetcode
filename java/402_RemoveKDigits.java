/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

public class Solution {
	public String removeKdigits(String num, int k) {
		// idea: use stack, traverse the string and push the digits we need into stack. when
		// encounter a new digit, pop the nums that larger than it from the stack as long as
		// k still > 0, k is # digits that still need to be removed.	O(n) Time, O(n-k) Space.
		// remember to consider cases like "1111". Use StringBuilder to construct the res.
		int len = num.length();
		if (k >= len) {
			return "0";
		}
		Stack<Character> stack = new Stack<Character>();
		int i = 0;
		while (i < num.length()) {
			// if encounter a smaller digit, remove previous larger ones as many as possible
			while (k > 0 && !stack.empty() && num.charAt(i) < stack.peek()) {
				stack.pop();
				k--;
			}
			stack.push(num.charAt(i));
			i++;
		}
		while (k > 0) {	// to deal with cases like "1111"
			stack.pop();
			k--;
		}
		// construct the number from the stack
		StringBuilder sb = new StringBuilder();
		while (!stack.empty()) {
			sb.append(stack.pop());
		}
		// deleting a character at the head is more expensive than deleting one at the tail
		while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0') {
			sb.deleteCharAt(sb.length() - 1);	// remove 0's at the tail to avoid leading zero after reversion
		}
		return sb.reverse().toString();
	}
}