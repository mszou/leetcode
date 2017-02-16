/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.
 * You may assume that the given expression is always valid.
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * Note: Do not use the eval built-in library function.
 */

public class Solution {
    public int calculate(String s) {
        // idea: use stack for parentheses, set the sign 1 (for '+') or -1 (for '-').   O(n) Time.
        // There are 5 possible types of character we may meet, and we need to do different operations:
		// 1. digit: Character.isDigit() checks digits, use a variable to store the current number;
		// 2. '+': indicates that previous num is over, so add the previous num and start a new one;
		// 3. '-': similar to '+', the difference is that we need to set the sign as -1 for new num;
		// 4. '(': push previous res & sign into stack, reset both, calculate res within the parentheses.
		// 5. ')': pop two from the stack (the previous sign & res before this pair of parentheses),
        //         and take (currentRes * sign + previousRes) as current res after the parentheses.
    	int len = s.length();
    	int res = 0, sign = 1; // temporary result so far; the sign for next number
    	Stack<Integer> stack = new Stack<Integer>();
    	for (int i = 0; i < len; i++) {
    		if (Character.isDigit(s.charAt(i))) {
    			int num = s.charAt(i) - '0';
    			while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
    				num = num * 10 + s.charAt(i + 1) - '0';
    				i++;
    			}
    			res += num * sign;
    		} else if (s.charAt(i) == '+') {
    			sign = 1;
    		} else if (s.charAt(i) == '-') {
    			sign = -1;
    		} else if (s.charAt(i) == '(') {  // push current res & the sign before '(' into the stack
    			stack.push(res);
    			stack.push(sign);
    			res = 0;
    			sign = 1;    // reset the result and sign, compute result in the parentheses
    		} else if (s.charAt(i) == ')') {  // finish computation within this pair of parentheses
    			res = res * stack.pop() + stack.pop();
    		}
    	}
    	return res;
    }
}