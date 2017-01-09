/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */

public class Solution {
	public void setZeroes(int[][] matrix) {
		// naive: use a new matrix (O(mn) Space), use a new row and column to record (O(m+n) Space).
		// idea: use the first row and column to record the position of 0's, i.e. if matrix[i][j] == 0,
		// set matrix[0][j] and matrix[i][0] as 0. need to treat first row & first column differently.
		// O(mn) Time, O(1) Space.
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean row0 = false, col0 = false;	// indicate whether first row / col has 0 elements
		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				row0 = true;
				break;
			}
		}
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				col0 = true;
				break;
			}
		}
		// mark in the first row and first column for 0 elements in the body
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		// set rows and columns for 0 elements in the body
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// set first row and first column
		if (row0) {
			for (int i = 0; i < n; i++) {
				matrix[0][i] = 0;
			}
		}
		if (col0) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
		return;
	}
}