/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */

public class Solution {
    public boolean isMatch(String s, String p) {
    	// sol 1: use two pointers, one for string and one for pattern. Time O(mn)
        int p1 = 0, p2 = 0;
        int match = 0;	// pointer used for matching '*'
        int starIdx = -1;	// the pos of '*' to be matched
        while (p1 < s.length()) {
            if (p2 < p.length() && (p.charAt(p2) == '?' || s.charAt(p1) == p.charAt(p2) && p.charAt(p2) != '*')) {
                p1++; p2++;	// advance both pointers if characters match
            } else if (p2 < p.length() && p.charAt(p2) == '*') {	// if '*' found
                starIdx = p2;	// set the starIdx at current position of p2
                match = p1;		// set the match at current position of p1
                p2++;	// advance pattern pointer to the character after '*'
            } else if (starIdx != -1) {	// current chars not match, there is a '*' before
                p2 = starIdx + 1;	// set p2 right after the last '*' (try matching '*' a longer string)
                p1 = ++match;	// advance string pointer
            } else {	// not any possible case that could match
            	return false;	
            }
        }        
        // if still has remaining characters in pattern
        while (p2 < p.length() && p.charAt(p2) == '*') {
        	p2++;	// skip all succeding '*'s
        }
        return p2 == p.length();

        // // sol 2: DP. dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1]. Time O(mn), Spce O(mn).
        // // (optimization?): use 1d DP to save space to o(n)
        // boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // dp[0][0] = true;	// for i > 0, dp[i][0] = false by default;
        // for (int j = 1; j <= p.length(); j++) {
        // 	if (p.charAt(j - 1) == '*') {
        // 		dp[0][j] = true;	// set true for the stars at the beginning
        // 	} else {
        // 		break;
        // 	}
        // }
        // for (int i = 1; i <= s.length(); i++) {
        // 	for (int j = 1; j <= p.length(); j++) {
        // 		if (p.charAt(j - 1) != '*') {	// characters must match
        // 			dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '?' || s.charAt(i-1)==p.charAt(j-1));
        // 		} else {
        // 			dp[i][j] = dp[i-1][j] || dp[i][j-1];
        // 		}
        // 	}
        // }
        // return dp[s.length()][p.length()];
    }
}