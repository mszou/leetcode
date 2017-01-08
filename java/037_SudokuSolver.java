/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 */

public class Solution {
    public void solveSudoku(char[][] board) {
        // idea: back-tracking
        solver(board);
    }

    private boolean solver(char[][] board) {
    	if (board == null || board.length != 9 || board[0].length != 9) {
    		return false;
    	}
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			if (board[i][j] == '.') {	// empty cell
    				for (char c = '1'; c <= '9'; c++) {
    					if (isValid(board, i, j, c)) {	// putting c in this cell is valid
    						board[i][j] = c;
    						if (solver(board)) {
    							return true;
    						}
    						board[i][j] = '.';	// backtracking, resume '.' at the cell
    					}
    				}
    				return false;	// 1-9 all invalid, then false, so go back and backtracking
    			}
    		}
    	}
    	return true;
    }

    // check whether putting c in board[i][j] is valid
    private boolean isValid(char[][] board, int i, int j, int c) {
    	// check row
    	for (int col = 0; col < 9; col++) {
    		if (board[i][col] == c) {
    			return false;
    		}
    	}
    	// check column
    	for (int row = 0; row < 9; row++) {
    		if (board[row][j] == c) {
    			return false;
    		}
    	}
    	// check 3 * 3 sub-box
    	for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
    		for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
    			if (board[row][col] == c) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}