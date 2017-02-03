// Find maxium square inside a sqaure, LC221
/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 */
// idea: DP. dp[i][j] records the side length of the largest all-one square
// whose bottom-right corner is matrix[i-1][j-1].    O(mn) Time, O(mn) Space.
public class MaximalSquare {
	public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int res = 0;    // record the side length of the maximal square so far
        int[][] dp = new int[m + 1][n + 1];
        // initialization: dp[0][j] & dp[i][0] are 0 by default
        for (int i = 1; i <= m; i++) {
        	for (int j = 1; j<= n; j++) {
        		if (matrix[i - 1][j - 1] == '1') {
                    // by doing the following, we check the elements above (i-1, j-1) and on its left
        			dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
        			res = Math.max(dp[i][j], res);   // update the res if find a larger one
        		}
        	}
        }
        return res * res;
    }

	public static void main(String[] args) {
		MaximalSquare s = new MaximalSquare();
		char[][] matrix = {{'0','1','0'},{'0','1','1'},{'1','1','1'}};
		System.out.println(s.maximalSquare(matrix));
	}

}
