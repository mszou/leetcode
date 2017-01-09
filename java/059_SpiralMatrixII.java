/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */

public class Solution {
	public int[][] generateMatrix(int n) {
		// idea: record the beginning and end of rows and columns, go spirally and
		// assign values from 1 to n^2.	O(n^2) Time. 
		int[][] res = new int[n][n];
		int rowBegin = 0, rowEnd = n - 1;
		int colBegin = 0, colEnd = n - 1;
		int num = 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// assign values for top row (from left to right)
			for (int i = colBegin; i <= colEnd; i++) {
				res[rowBegin][i] = num++;
			}
			rowBegin++;
			// assign values for right column (from top to bottom)
			for (int i = rowBegin; i <= rowEnd; i++) {
				res[i][colEnd] = num++;
			}
			colEnd--;
			// assign values for bottom row if exists (from right to left)
			if (rowBegin <= rowEnd) {
				for (int i = colEnd; i >= colBegin; i--) {
					res[rowEnd][i] = num++;
				}
				rowEnd--;
			}
			// assign values for left column if exists (from bottom to top)
			if (colBegin <= colEnd) {
				for (int i = rowEnd; i >= rowBegin; i--) {
					res[i][colBegin] = num++;
				}
				colBegin++;
			}
		}
		return res;
	}
}