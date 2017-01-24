/**
 * Given two strings, find the longest common substring. Return the length of it.
 * Notice
 * The characters in substring should occur continuously in original string. This is different with subsequence.
 * Example
 * Given A = "ABCD", B = "CBCE", return 2.
 * Challenge 
 * O(n x m) time and memory.
 */

public class Solution {
	/**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
	public int longestCommonSubstring(String A, String B) {
		// idea: 2D-DP. dp[i][j] is the length of the longest common substring ended with
		// A[i-1] & B[j-1] in A[0..i-1] & B[0..j-1]
		int n = A.length();
		int m = B.length();
		int[][] dp = new int[n + 1][m + 1];
		int maxLen = 0;
		// initialization: dp[i][j] is 0 by default
		// update rule: dp[i][j] = f[i-1][j-1] + 1 (if A[i-1] == B[j-1]) or 0 (if A[i-1] != B[j-1])
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = 0;
				}
				maxLen = Math.max(maxLen, dp[i][j]);	// update maxLen if find a longer one
			}
		}
		return maxLen;
	}
}