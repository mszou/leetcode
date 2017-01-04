/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * Follow up: 
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */

public class Solution {
	// sol 1: naive, use a new matrix to store the next state for every cell. O(mn) Time, O(mn) Space.
	
	// sol 2: in-place, state trasition. add a bit before the current bit as its next state, i.e. the
	// number in each cell becomes 2 bits: bit0 is current state, bit1 (by default 0) is next state.
	// Then trasitions are: 00(0): dead->dead; 01(1): live->dead; 10(2): dead->live; 11(3): live->live
	// bit1 is by default 0, so only need to care about transitions to live. For each cell, check the
	// neighbors arount it and set bit1. do "& 1" to get the current state, ">> 1" to get next state.
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = liveNeighbors(board, m, n, i, j);
				// 2nd bit is by default 0, so only need to care about when 2nd bit becomes 1
				if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
					board[i][j] = 3;	// 01 -> 11 (Condition 2)
				}
				if (board[i][j] == 0 && lives == 3) {
					board[i][j] = 2;	// 00 -> 10 (Condition 4)
				}
			}
		}
		// update states
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] >>= 1;	// Get next state (bit1)
			}
		}
	}

	private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
		int lives = 0;
		for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
			for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
				lives += board[x][y] & 1;	// add up the current lives of neighbors
			}
		}
		lives -= board[i][j] & 1;	// exclude itself
		return lives;
	}
}