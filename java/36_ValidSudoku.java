/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * A partially filled sudoku which is valid.
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */

public class Solution {
	// sol 1: use 3 matrices to indicate whether a number has occurred in this row/colmn/sub-box
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
		boolean[][] occurredRow = new boolean[9][9];
		boolean[][] occurredCol = new boolean[9][9];
		boolean[][] occurredBox = new boolean[9][9];
		// traverse the matrix
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int k = i / 3 * 3 + j / 3;	// index of sub-box
					int num = board[i][j] - '1';
					if (num < 0 || num > 8) {	// Sudoku numbers are 1-9, with index 0-8
						return false;
					}
					if (occurredRow[i][num] || occurredCol[j][num] || occurredBox[k][num]) {
						return false;
					}
					occurredRow[i][num] = occurredCol[j][num] = occurredBox[k][num] = true;
				}
			}
		}
		return true;	// if no invalid entry, then valid
	}

	// sol 2: check each row, column, and sub-box separately for invalid entries
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
		boolean[] visited = new boolean[9];
		// for 9 rows
		for (int i = 0; i < 9; i++) {
			Arrays.fill(visited, false);
			for (int j = 0; j < 9; j++) {
				if (!checkValidness(visited, board[i][j])) {
					return false;
				}
			}
		}
		// for 9 columns
		for (int j = 0; j < 9; j++) {
			Arrays.fill(visited, false);
			for (int i = 0; i < 9; i++) {
				if (!checkValidness(visited, board[i][j])) {
					return false;
				}
			}
		}
		// for 9 sub-boxes
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				Arrays.fill(visited, false);
				for (int k = 0; k < 9; k++) {
					if (!checkValidness(visited, board[i + k / 3][j + k % 3])) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean checkValidness(boolean[] visited, char c) {
		if (c == '.') {
			return true;
		}
		int num = c - '0';
		if (num < 1 || num > 9 || visited[num - 1]) {
			return false;
		}
		visited[num - 1] = true;
		return true;
	}
}