/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */

public class Solution {
	// idea: backtracking. Use an array to record the position for queens in each column, then for each row,
	// check each column to place a queen, DFS then backtracking to get all solutions
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		int[] row = new int[n];	// to record the position of queens in each column, i.e. row[i] = j means the queen for Column i is at Row j
		solver(res, row, n, 0);
		return res;
	}

	private void solver(List<List<String>> res, int[] row, int n, int rowNum) {
		if (rowNum == n) {
			List<String> sol = new ArrayList<String>();
			sol = translateString(row);
			res.add(sol);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (isValid(row, rowNum, i)) {
				row[rowNum] = i;
				solver(res, row, n, rowNum + 1);
				row[rowNum] = 0;	// backtracking
			}
		}
		return;
	}

	// translate int[] row to the corresponding List<String> showing the solution
	private List<String> translateString(int[] row) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < row.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < row.length; j++) {
				if (j == row[i]) {
					sb.append('Q');
				} else {
					sb.append('.');
				}
			}
			list.add(sb.toString());
		}
		return list;
	}

	// check whether putting queen at (rowIdx, colIdx) is valid so far
	private boolean isValid(int[] row, int rowIdx, int colIdx) {
		for (int i = 0; i < rowIdx; i++) {	// check if queen in Row i can attack
			if (row[i] == colIdx) {	// already have one queen in this column
				return false;
			}
			if (Math.abs(row[i] - colIdx) == (rowIdx - i)) {
				return false;	// queen can go diagonally
			}
		}
		return true;
	}
}