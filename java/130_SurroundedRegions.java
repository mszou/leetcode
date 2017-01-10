/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */

public class Solution {
	// idea: DFS. only 2 kinds of 'O's will not be flipped: 1.on the edge; 2.adjacent to an unflippable 'O'
	// so start from 'O's on the edge, DFS to mark all unflippable 'O's, then flip other 'O's.	O(n^2) Time.
	public final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};	// four directions

	public void solve(char[][] board) {
		if (board == null || board.length < 2 || board[0].length < 2) {
			return;
		}
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O') {	// left edge
				dfs(board, i, 0);
			}
			if (board[i][n - 1] == 'O') {	// right edge
				dfs(board, i, n - 1);
			}
		}
		for (int j = 1; j < n - 1; j++) {
			if (board[0][j] == 'O') {	// upper edge
				dfs(board, 0, j);
			}
			if (board[m - 1][j] == 'O') {	// bottom edge
				dfs(board, m - 1, j);
			}
		}
		// flip captured 'O's, unflippable 'O's were marked as '*'s
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if (board[i][j] == '*') {
					board[i][j] = 'O';
				}
			}
		}
	}

	// do dfs and mark vistited 'O's as '*'s.
	private void dfs(char[][] board, int x, int y) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return;
		}
		if (board[x][y] == 'O') {
			board[x][y] = '*';
		}
		for (int[] dir : directions) {	// search 4 directions
			int i = x + dir[0], j = y + dir[1];
			if (i > 0 && i < board.length - 1 && j > 0 && j < board[0].length - 1 && board[i][j] == 'O') {
				dfs(board, i, j);
			}
		}
	}
}