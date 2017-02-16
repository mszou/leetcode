/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb. 
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 * Example:
 * For the given grid
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

public class Solution {
	public int maxKilledEnemies(char[][] grid) {
		// idea: DP, traverse the grid, count the number of hits in the row and col. Since our traversal
		// is row-first, so we only need a variable to record # enemies killed in the row, but need an
		// array to memorize the # enemies killed in the cols before.	O(mn) Time, O(n) Space.
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length, n = grid[0].length;
		int res = 0;
		int killedInRow = 0;
		int[] killedInCol = new int[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') {
					killedInRow = 0;	// reset killedInRow
					for (int k = j; k < n && grid[i][k] != 'W'; k++) {
						if (grid[i][k] == 'E') {
							killedInRow++;
						}
					}
				}
				if (i == 0 || grid[i - 1][j] == 'W') {
					killedInCol[j] = 0;
					for (int k = i; k < m && grid[k][j] != 'W'; k++) {
						if (grid[k][j] == 'E') {
							killedInCol[j]++;
						}
					}
				}
				if (grid[i][j] == '0') {
					res = Math.max(res, killedInRow + killedInCol[j]);
				}
			}
		}
		return res;
	}
}