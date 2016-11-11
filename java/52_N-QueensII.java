/**
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */

public class Solution {
	// idea: backtracking, record the position for queens in each column
	public static int count;

	public int totalNQueens(int n) {
		count = 0;
		if (n <= 0) {
			return count;
		}
		int[] usedCol = new int[n];
		solver(usedCol, n, 0);
		return count;
	}

	private void solver(int[] usedCol, int n, int rowNum) {
		if (rowNum == n) {	// found a solution
			count++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (isValid(usedCol, rowNum, i)) {
				usedCol[rowNum] = i;
				solver(usedCol, n, rowNum + 1);
				usedCol[rowNum] = 0;	// backtracking
			}
		}
		return;
	}

	private boolean isValid(int[] usedCol, int rowIdx, int colIdx) {
		for (int i = 0; i < rowIdx; i++) {
			if (usedCol[i] == colIdx) {
				return false;
			}
			if ((rowIdx - i) == Math.abs(colIdx - usedCol[i])) {
				return false;
			}
		}
		return true;
	}
}