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
    	// idea: recursive. DFS + backtracking. suppose we have i remaining left parentheses and j
        // right parentheses to add, we can add '(' as long as i > 0, and add ')' only when j > i.
        // so in each situation, we try '(' first, then backtracking and add ')'.   O(2^n) Time?
        List<String> res = new ArrayList<String>();
        if (n <= 0) {
            return res;
        }
        generate(res, "", n, n);
        return res;
    }
    
    // generate parenthesis using leftNum '(' and rightNum ')'
    public void generate(List<String> res, String s, int leftNum, int rightNum) {
        if (leftNum == 0 && rightNum == 0) {
            res.add(s);
            return;
        }
        if (leftNum > 0) {
        	generate(res, s + "(", leftNum - 1, rightNum);
        }
        if (rightNum > leftNum) {
        	generate(res, s + ")", leftNum, rightNum - 1);
        }
    }
}