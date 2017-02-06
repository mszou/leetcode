/**
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */

public class Solution {
	public int countBattleships(char[][] board) {
		// idea: only take the first cell of each battleship into account, i.e. the top cell of a
		// vertical ship or the left-most cell of a horizontal ship. In this way, when we traverse
		// the board, check if a new 'X' has an 'X' above or on the left.	O(n^2) Time, O(1) Space.
		if (board == null || board.length == 0 || board[0].length == 0) {
			return 0;
		}
		int m = board.length, n = board[0].length;
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				if (i > 0 && board[i - 1][j] == 'X') {	// in a vertical battleship
					continue;
				}
				if (j > 0 && board[i][j - 1] == 'X') {	// in a horizontal battleship
					continue;
				}
				count++;
			}
		}
		return count;
	}
}