/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * You may assume the following rules:
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * TicTacToe toe = new TicTacToe(3);
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | | // Player 1 makes a move at (0, 0).
 * | | | |
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | | // Player 2 makes a move at (0, 2).
 * | | | |
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | | // Player 1 makes a move at (2, 2).
 * | | |X|
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| | // Player 2 makes a move at (1, 1).
 * | | |X|
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| | // Player 1 makes a move at (2, 0).
 * |X| |X|
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| | // Player 2 makes a move at (1, 0).
 * |X| |X|
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| | // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n^2) per move() operation?
 * Hint:
 * Could you trade extra space such that move() operation can be done in O(1)?
 * You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 */

public class TicTacToe {
	// sol 1: Straightforward, build a n*n board, for each cell, 0 means empty, 1 means a
	// mark by Player1, 2 means a mark by Player2. Every time make a move, scan every row,
	// every col, and diagonals to see if there are n same marks.	O(n^2) Time, O(n^2) Space.

	// sol 2: only keep a count for each row and col and diagonals. Use '1' for marks
	// by Player1, and '-1' for marks by Player2, then either player win can be checked
	// by |a single row/col/diagonal sum| == n.		O(1) Time, O(1) Space.
	private int[] rows;
	private int[] cols;
	private int diagonal;
	private int antiDiagonal;

	/** Initialize your data structure here. */
	public TicTacToe(int n) {
		rows = new int[n];
		cols = new int[n];
	}
	/** Player {player} makes a move at ({row}, {col}).
		@param row The row of the board.
		@param col The column of the board.
		@param player The player, can be either 1 or 2.
		@return The current winning condition, can be either:
			0: No one wins.
			1: Player 1 wins.
			2: Player 2 wins. */
	public int move(int row, int col, int player) {
		int n = rows.length;
		if (row < 0 || row >= n || col < 0 || col >= n || player != 1 && player != 2) {
			return -1;	// invalid move
		}
		int toAdd = (player == 1) ? 1 : -1;	// add 1 for Player 1, -1 for Player 2
		rows[row] += toAdd;
		cols[col] += toAdd;
		if (row == col) {	// on the diagonal
			diagonal += toAdd;
		}
		if (row + col == n - 1) {	// on the anti diagonal
			antiDiagonal += toAdd;
		}
		if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
			return player;
		}
		return 0;
	}

}