/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */

public class Solution {
    public int minDistance(String word1, String word2) {
        // idea: DP. O(mn) Time. dp[i][j] is the minimum number of operations to convert word1[0..i-1] to word2[0..j-1]. 
        // Boundary cases: dp[i][0] = i, dp[0][j] = j; suppose word1[i-1] == c, word2[j-1] == d: 
        // 0. If c == d, dp[i][j] = dp[i-1][j-1]; otherwise
        // 1. insert d, dp[i][j] = dp[i][j-1] + 1; (convert word1[0..i-1] to word2[0..j-2], then insert word2[j-1])
        // 2. delete c, dp[i][j] = dp[i-1][j] + 1; (convert word1[0..i-2] to word2[0..j-1], then delete word1[i-1])
        // 3. replace c by d, dp[i][j] = dp[i-1][j-1] + 1;  // let dp[i][j] = min(case1, case2, case3)
        int m = word1.length(), n = word2.length();
        // 2D DP: O(mn) Space
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1] + 1, dp[i-1][j] + 1), dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[m][n];

    	// // optimize: O(m) Space. In sol 1, each time we update dp[i][j], we only need dp[i-1][j-1], dp[i][j-1], dp[i-1][j].
    	// // In fact, we need not maintain the full m*n matrix. Instead, maintaing one column is enough.
    	// // use 'pre' to record dp[i - 1][j - 1], cur[i] records dp[i][j]
    	// int[] cur = new int[m + 1];
    	// for (int i = 1; i <= m; i++) {
    	// 	cur[i] = i;
    	// }
    	// for (int j = 1; j <= n; j++) {	// outer loop traverse j
    	// 	int pre = cur[0];
    	// 	cur[0] = j;
    	// 	for (int i = 1; i <= m; i++) {	// inner loop traverse i
    	// 		int temp = cur[i];	// here cur[i] is dp[i][j-1], updated in last outer loop (for j-1)
    	// 		if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
    	// 			cur[i] = pre;
    	// 		} else {
    	// 			cur[i] = Math.min(Math.min(cur[i] + 1, cur[i - 1] + 1), pre + 1);
    	// 		}
    	// 		pre = temp;
    	// 	}
    	// }
    	// return cur[m];
    }
}