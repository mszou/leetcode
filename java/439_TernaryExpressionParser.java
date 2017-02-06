/**
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
 * Note:
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * Example 1:
 * Input: "T?2:3"
 * Output: "2"
 * Explanation: If true, then result is 2; otherwise result is 3.
 * Example 2:
 * Input: "F?1:T?4:5"
 * Output: "4"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 *           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 *           -> "4"                                    -> "4"
 * Example 3:
 * Input: "T?T?F:5:3"
 * Output: "F"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *              "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 *           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 *           -> "F"                                    -> "F"
 */

public class Solution {
	public String parseTernary(String expression) {
		// idea: use stack. Based on the constraint of single digit and T/F, we can iterate the expression from tail,
		// push the results from right to left. when encounter a '?', pop two res from stack, then check the character
		// before '?', choose the corresponding res and push it back to stack.	one-pass, O(n) Time, O(n) Space.
		if (expression == null || expression.length() == 0) {
			return "";
		}
		Stack<Character> stack = new Stack<>();
		stack.push(expression.charAt(expression.length() - 1));
		for (int i = expression.length() - 3; i >= 0; i-= 2) {	// take advantage of the constraint of single digit
			if (expression.charAt(i + 1) == '?') {
				char trueRes = stack.pop();
				char falseRes = stack.pop();
				stack.push(expression.charAt(i) == 'T' ? trueRes : falseRes);
			} else {
				stack.push(expression.charAt(i));
			}
		}
		return String.valueOf(stack.peek());
	}
}