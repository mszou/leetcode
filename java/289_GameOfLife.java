/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Condition 1: Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Condition 2: Any live cell with two or three live neighbors lives on to the next generation.
 * Condition 3: Any live cell with more than three live neighbors dies, as if by over-population..
 * Condition 4: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * Follow up: 
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */

public class Solution {
	// sol 1: naive, use a new matrix to store the next state for every cell. O(mn) Time, O(mn) Space.
	
	// sol 2: in-place, state trasition. use 2-bit num (0~3) to indicate states of each cell: the lower
	// bit is same as current value (0 or 1), the higher bit (0 by default) shows next state. Then 0~3
	// can show 4 trasitions: 0(00): dead->dead; 1(01): live->dead; 2(10): dead->live; 3(11): live->live
	// higher bit is by default 0, so only need to care about transitions to live. For each cell, check
	// the neighbors arount it and set bit1. can do "& 1" to get current state, ">> 1" to get next state.
	// Next state is stored in the cell, so no interfere with others during update.	O(mn) Time, O(1) Space.
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = liveNeighbors(board, m, n, i, j);	// count the live neighbors
				// higher bit is by default 0, so only need to care about when it becomes 1
				if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
					board[i][j] = 3;	// 01 -> 11 (Condition 2, keep living)
				}
				if (board[i][j] == 0 && lives == 3) {
					board[i][j] = 2;	// 00 -> 10 (Condition 4, revive)
				}
			}
		}
		// update states for every cell
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] >>= 1;	// Get next state (bit1)
			}
		}
	}

	private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
		int lives = 0;
		// count the lives in the 3 * 3 cells around (i, j) and exclude itself
		for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
			for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
				if (x == i && y == j) {
					continue;	// skip itself
				}
				lives += board[x][y] & 1;	// add up the current lives of neighbors
			}
		}
		return lives;
	}
}