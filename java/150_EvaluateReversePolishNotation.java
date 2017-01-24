/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

public class Solution {
	public int evalRPN(String[] tokens) {
		// idea: use stack, push integers into stack, when an operator occurs, pop two numbers from
		// the stack, compute the expression and push the result.
		Stack<Integer> stack = new Stack<Integer>();
		String operators = "+-*/";
		for (String token : tokens) {
			if (!operators.contains(token)) {	// not an operator, i.e. is an integer
				stack.push(Integer.valueOf(token));
				continue;
			}
			// otherwise, it's an operator, so pop two integers for computation
			int num2 = stack.pop();
			int num1 = stack.pop();
			if (token.equals("+")) {
				stack.push(num1 + num2);
			} else if (token.equals("-")) {
				stack.push(num1 - num2);
			} else if (token.equals("*")) {
				stack.push(num1 * num2);
			} else {
				stack.push(num1 / num2);
			}
		}
		// if (stack.size() != 1) {
		// 	return -1;	// not a valid arithmetic expression in Reverse Polish Notation
		// }
		return stack.pop();
	}
}