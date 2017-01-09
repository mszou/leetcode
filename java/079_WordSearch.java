/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */

public class Solution {
	// idea: DFS + backtracking
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word.charAt(0)) {	// match the first letter, then start searching
					if (find(board, i, j, word, 0)) {
						return true;	// counter +1 if requires to count the number
					}
				}
			}
		}
		return false;
	}

	private boolean find(char[][] board, int row, int col, String word, int index) {
		if (index == word.length()) {
			return true;
		}
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return false;
		}
		if (board[row][col] == word.charAt(index)) {	// find a match, then search further
			char c = board[row][col];
			board[row][col] = '#';	// indicate that this char has been used, can also use "board[row][col] ^= 256"
			boolean exists = find(board, row + 1, col, word, index + 1) || find(board, row - 1, col, word, index + 1) || find(board, row, col + 1, word, index + 1) || find(board, row, col - 1, word, index + 1);
			board[row][col] = c;	// backtracking, use "board[row][col] ^= 256" again if is used above
			return exists;
		}
		return false;
	}
}