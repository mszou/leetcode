/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */

public class Solution {
    public List<String> generateParenthesis(int n) {
    	// idea: recursive. Think of the rule of adding parenthesis, suppose the number of remaining
        // left & right parentheses are leftNum & rightNum, then we can add "(" as long as leftNum > 0,
        // add ")" only when rightNum > leftNum, so try "(" first, then backtracking. O(2^n) Time?
        List<String> res = new ArrayList<String>();
        if (n <= 0) {
            return res;
        }
        getPair(res, "", n, n);
        return res;
    }
    
    // generate parenthesis using leftNum '(' and rightNum ')'
    public void getPair(List<String> res, String s, int leftNum, int rightNum) {
        if (leftNum == 0 && rightNum == 0) {
            res.add(s);
            return;
        }
        if (leftNum > 0) {
        	getPair(res, s + "(", leftNum - 1, rightNum);
        }
        if (rightNum > leftNum) {
        	getPair(res, s + ")", leftNum, rightNum - 1);
        }
    }
}