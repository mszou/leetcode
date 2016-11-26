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
        // idea: use a stack to deal with parentheses, calculate plus and minus by setting the sign 1 or -1
        // 5 possible types of input, and we need to do different operations:
		// 1. digit: it should be one digit from the current number, use Character.isDigit() to check
		// 2. '+': number is over, we can add the previous number and start a new number
		// 3. '-': same as '+', need to set the sign as -1
		// 4. '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
		// 5. ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
    	int len = s.length();
    	int res = 0, sign = 1;
    	Stack<Integer> stack = new Stack<Integer>();
    	for (int i = 0; i < len; i++) {
    		if (Character.isDigit(s.charAt(i))) {
    			int sum = s.charAt(i) - '0';
    			while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
    				sum = sum * 10 + s.charAt(i + 1) - '0';
    				i++;
    			}
    			res += sum * sign;
    		} else if (s.charAt(i) == '+') {
    			sign = 1;
    		} else if (s.charAt(i) == '-') {
    			sign = -1;
    		} else if (s.charAt(i) == '(') {  // push the result so far and the sign before '(' into the stack
    			stack.push(res);
    			stack.push(sign);
    			res = 0;
    			sign = 1;    // reset the result and sign, compute result in the parentheses
    		} else if (s.charAt(i) == ')') {
    			res = res * stack.pop() + stack.pop();
    		}
    	}
    	return res;
    }
}