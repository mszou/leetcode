/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class Solution {
    public boolean isValid(String s) {
    	// idea: use stack and a user-defined function to check the validness
        Stack<Character> stack = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if ("([{".contains(String.valueOf(c))) {
                stack.push(c);	// push left parentheses
            } else {
                if (!stack.isEmpty() && isValidPair(stack.peek(), c)) {
                    stack.pop();	// pop if construct a valid pair
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    private boolean isValidPair(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }
}