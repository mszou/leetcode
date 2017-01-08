/**
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */

public class Solution {
    public boolean isMatch(String s, String p) {
        // idea: use 2D DP. dp[i][j] is the result for matching s.substring(0,i) with p.substring(0,j)
        // 1. If p.charAt(j) == s.charAt(i) :  dp[i+1][j+1] = dp[i][j];
        // 2. If p.charAt(j) == '.' : dp[i+1][j+1] = dp[i][j];
        // 3. If p.charAt(j) == '*', here are two sub conditions:
        //	(1) if p.charAt(j-1) != s.charAt(i) : dp[i+1][j+1] = dp[i+1][j-1]  // a* only counts as empty
        //	(2) if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.':
        //			dp[i+1][j+1] = dp[i][j+1]	// in this case, a* counts as multiple a
        //		or 	dp[i+1][j+1] = dp[i+1][j]   // in this case, a* counts as single a
        //		or 	dp[i+1][j+1] = dp[i+1][j-1]   // in this case, a* counts as empty
        if (!p.isEmpty() && p.charAt(0) == '*') {
        	return false;	// invalid p
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // initialization: f(0,0)=true, f(k,0) & f(0,2k-1) where k>0 are false by default
        dp[0][0] = true;
        for (int i = 1; i < p.length(); i++) {	// f(0,i+1) is same as f(0,i-1) if p.charAt(i) == '*'
        	if (p.charAt(i) == '*' && dp[0][i-1]) {
        		dp[0][i+1] = true;
        	}
        }
        for (int i = 0; i < s.length(); i++) {
        	for (int j = 0; j < p.length(); j++) {
        		if (p.charAt(j) == '.') {
        			dp[i+1][j+1] = dp[i][j];
        		} else if (p.charAt(j) == '*') {
        			dp[i+1][j+1] = dp[i+1][j-1] || dp[i][j+1] && isCharMatch(s.charAt(i), p.charAt(j-1));
        		} else {
        			dp[i+1][j+1] = dp[i][j] && s.charAt(i) == p.charAt(j);
        		}
        	}
        }
        return dp[s.length()][p.length()];
    }
    
    private boolean isCharMatch(char s, char p) {
        return p == '.' || s == p;
    }
}