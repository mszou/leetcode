/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */

public class Solution {
    public int calculate(String s) {
        // idea: use a stack
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0, res = 0;
        char op = '+';	// record the previous operator
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (Character.isDigit(c)) {
        		num = Math.min(num * 10 + (c - '0'), Integer.MAX_VALUE);	// in case of overflow
        	}
        	if ("+-*/".contains(String.valueOf(c)) || i == s.length() - 1) {
        		if (op == '+') {
        			stack.push(num);
        		} else if (op == '-') {
        			stack.push(-num);
        		} else {
        			res -= stack.peek();	// subtract top before mul/div
        			if (op == '*') {
        				stack.push(stack.pop() * num);
        			} else {
        				stack.push(stack.pop() / num);
        			}
        		}
        		op = c;
        		num = 0;
        		res += stack.peek();
        	} /* else whitespace, skip*/
        }
        return res;
    }
}