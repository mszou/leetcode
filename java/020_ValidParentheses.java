/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class Solution {
    public boolean isValid(String s) {
    	// idea: use stack. Traverse the string, if meet a '(', push into the stack; otherwise, i.e. meet a ')',
        // check empty stack and whether it forms a valid pair with the top of stack.    O(n) Time, O(n) Space.
        Stack<Character> stack = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if ("([{".contains(String.valueOf(c))) {
                stack.push(c);	// push left parentheses
            } else {    // c is a right parenthesis
                if (!stack.isEmpty() && isValidPair(stack.peek(), c)) {
                    stack.pop();	// pop the top '(' if construct a valid pair
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty(); // all characters should be matched in valid string, so no char left
    }
    
    private boolean isValidPair(char c1, char c2) { // only 3 valid cases
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }
}