/**
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * Example:
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */

public class Solution {
	public int[][] multiply(int[][] A, int[][] B) {
		// idea: Suppose AB = C, then C[i][j] = sum of A[i][k] * B[k][j] on all k(0~n). Since
		// they are sparse matrices, we only need to add the non-zero product, i.e. add up the
		// products of A[i][k] and B[k][j] when they are both non-zero.	O(mn)~O(m*n*nB) Time.
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];	// the result of AB should be m * nB matrix
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0) {
							C[i][j] += A[i][k] * B[k][j];
						}
					}
				}
			}
		}
		return C;
	}
}