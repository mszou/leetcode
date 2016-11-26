/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

public class Solution {
	public int evalRPN(String[] tokens) {
		// idea: use stack, push integers into stack, when an operator occurs,
		// pop two numbers from the stack, compute the expression and push the result.
		Stack<Integer> stack = new Stack<Integer>();
		String operators = "+-*/";
		for (String token : tokens) {
			if (!operators.contains(token)) {
				stack.push(Integer.valueOf(token));
				continue;
			}
			int a = stack.pop();
			int b = stack.pop();
			if (token.equals("+")) {
				stack.push(b + a);
			} else if (token.equals("-")) {
				stack.push(b - a);
			} else if (token.equals("*")) {
				stack.push(b * a);
			} else {
				stack.push(b / a);
			}
		}
		return stack.pop();
	}
}