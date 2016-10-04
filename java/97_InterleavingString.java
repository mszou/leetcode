/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */

public class Solution {
	/**
	 * @param s1, s2, s3 three strings
	 * @return whether s3 is formed by the interleaving of s1 and s2
	 */
    public boolean isInterleave(String s1, String s2, String s3) {
        // idea: 2D DP. dp[i][j] is whether s3[0,i+j) is interleaving of s1[0,i) & s2[0,j)
        if (s1.length() + s2.length() != s3.length()) {
        	return false;
        }
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {   // only use s1
        	if (s3.charAt(i - 1) == s1.charAt(i - 1) && dp[i - 1][0]) {
        		dp[i][0] = true;
        	} else {
        		break;
        	}
        }
        for (int j = 1; j <= len2; j++) {   // only use s2
        	if (s3.charAt(j - 1) == s2.charAt(j - 1) && dp[0][j - 1]) {
        		dp[0][j] = true;
        	} else {
        		break;
        	}
        }
        for (int i = 1; i <= len1; i++) {
        	for (int j = 1; j <= len2; j++) {
                // match the first (i+j) chars with the first i chars in s1 and first j chars in s2
        		if (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j] || s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
        			dp[i][j] = true;
        		}
        	}
        }
        return dp[len1][len2];
    }
}