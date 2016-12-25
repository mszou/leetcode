/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * nums = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * nums = [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

public class Solution {
	// idea: DFS from every cell, search 4 directions and only continue with valid cells
	// cache the max length from matrix[i][j] in cache[i][j] for possible use in future visit
	// each cell can be accessed at most 5 times: 4 from 4 directions and 1 from the outermost loop
	// max length from each cell will be computed only once, so the total Time complexity is: O(mn)

	public final int[][] directions = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length, n = matrix[0].length;
		int[][] cache = new int[m][n];
		int max = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int len = dfs(matrix, i, j, m, n, cache);	// the max length from (i,j)
				max = Math.max(max, len);
			}
		}
		return max;
	}

	// returns the max length of increasing path from matrix[i][j]
	public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
		if (cache[i][j] != 0) {
			return cache[i][j];
		}
		int max = 1;
		for (int[] dir : directions) {
			int x = i + dir[0], y = j + dir[1];
			if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
				continue;
			}
			int len = 1 + dfs(matrix, x, y, m, n, cache);
			max = Math.max(max, len);
		}
		cache[i][j] = max;
		return max;
	}
}