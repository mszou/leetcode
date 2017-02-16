/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */

public class Solution {
	public List<Integer> spiralOrder(int[][] matrix) {
		// idea: record the start and end of rows and columns, spirally traverse and add elements to result.
		// remember to check if the row/col unvisited for the second half spiral.	O(mn) Time, O(1) Space.
		List<Integer> res = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int rowBegin = 0, rowEnd = matrix.length - 1;
		int colBegin = 0, colEnd = matrix[0].length - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// traverse upper row (from left to right)
			for (int i = colBegin; i <= colEnd; i++) {
				res.add(matrix[rowBegin][i]);
			}
			rowBegin++;
			// traverse right column (from top to bottom)
			for (int i = rowBegin; i <= rowEnd; i++) {
				res.add(matrix[i][colEnd]);
			}
			colEnd--;
			// traverse bottom row (from right to left)
			if (rowBegin <= rowEnd) {	// need to check if there is still unfinished row
				for (int i = colEnd; i >= colBegin; i--) {
					res.add(matrix[rowEnd][i]);
				}
				rowEnd--;
			}
			// traverse left column (from bottom to top)
			if (colBegin <= colEnd) {
				for (int i = rowEnd; i >= rowBegin; i--) {
					res.add(matrix[i][rowBegin]);
				}
				colBegin++;
			}
		}
		return res;
	}
}