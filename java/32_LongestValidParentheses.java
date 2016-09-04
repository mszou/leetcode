/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

public class Solution {
    public int longestValidParentheses(String s) {
    	if (s == null || s.length() <= 1) {
    		return 0;
    	}
    	// // sol 1: use stack<indices of '('> + two pointers  (11ms)
    	// Stack<Integer> stack = new Stack<Integer>();
    	// int maxLen = 0, matchedLen = 0;
    	// int left = -1;	// record the leftBound of the current valid substring
    	// for (int i = 0; i < s.length(); i++) {
    	// 	if (s.charAt(i) == '(') {
    	// 		stack.push(i);
    	// 	} else {
    	// 		if (stack.isEmpty()) {	// no '(' to construct a pair
    	// 			left = i;	// so no valid parentheses containing this ')'
    	// 		} else {
    	// 			stack.pop();
    	// 			if (stack.isEmpty()) {
    	// 				matchedLen = i - left;
    	// 			} else {
    	// 				matchedLen = i - stack.peek();
    	// 			}
    	// 			maxLen = Math.max(maxLen, matchedLen);
    	// 		}
    	// 	}
    	// }

    	// sol 2: DP. dp[i] is the length of valid substring ending with s.charAt(i)    (4ms)
    	int[] dp = new int[s.length()];
    	int open = 0;	// count the number of '('
    	int maxLen = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == '(') {
    			open++;	// dp[i] for open parentheses are 0's by default
    		} else if (open > 0) {	// match a pair
    			dp[i] = 2 + dp[i - 1];
    			if (i > dp[i]) {
    				dp[i] += dp[i - dp[i]];
    			}
    			open--;
    			maxLen = Math.max(maxLen, dp[i]);
    		}
    	}

    	return maxLen;
    }
}