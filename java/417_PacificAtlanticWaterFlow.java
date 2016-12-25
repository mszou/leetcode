/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * Example:
 * Given the following 5x5 matrix:
 *   Pacific ~   ~   ~   ~   ~ 
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

public class Solution {
	// idea: DFS or BFS
	int[][] directions = new int[][]{{1, 0},{-1, 0}, {0, 1}, {0, -1}};

	// sol 1: BFS, do BFS from of top & left edges and mark all cells that can flow to Pacific, then do
	// the same to mark all cells that can flow to Atlantic, return the cells that can flow to both.
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> res = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int m = matrix.length, n = matrix[0].length;
		// two boolean matrices mark whether cells can flow to Pacific / Atlantic
		boolean[][] pacific = new boolean[m][n];
		boolean[][] atlantic = new boolean[m][n];
		// queue is used to store cells that can flow to P/A and haven't done bfs
		Queue<int[]> p_queue = new LinkedList<>();
		Queue<int[]> a_queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			p_queue.offer(new int[]{i, 0});
			a_queue.offer(new int[]{i, n - 1});
			pacific[i][0] = true;
			atlantic[i][n - 1] = true;
		}
		for (int j = 0; j < n; j++) {
			p_queue.offer(new int[]{0, j});
			a_queue.offer(new int[]{m - 1, j});
			pacific[0][j] = true;
			atlantic[m - 1][j] = true;
		}
		bfs(matrix, p_queue, pacific);
		bfs(matrix, a_queue, atlantic);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(new int[]{i, j});
				}
			}
		}
		return res;
	}

	private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int[] dir : directions) {
				int x = curr[0] + dir[0];
				int y = curr[1] + dir[1];
				if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] < matrix[curr[0]][curr[1]]) {
					continue;
				}
				visited[x][y] = true;
				queue.offer(new int[]{x, y});
			}
		}
	}


	// sol 2: DFS, do DFS from the cells on the edge and mark cells that can flow to P/A
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> res = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int m = matrix.length, n = matrix[0].length;
		boolean[][] pacific = new boolean[m][n];
		boolean[][] atlantic = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			dfs(matrix, pacific, i, 0, Integer.MIN_VALUE);
			dfs(matrix, atlantic, i, n - 1, Integer.MIN_VALUE);
		}
		for (int j = 0; j < n; j++) {
			dfs(matrix, pacific, 0, j, Integer.MIN_VALUE);
			dfs(matrix, atlantic, m - 1, j, Integer.MIN_VALUE);
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(new int[]{i, j});
				}
			}
		}
		return res;
	}

	private void dfs(int[][] matrix, boolean[][] visited, int x, int y, int height) {
		if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] < height) {
			return;
		}
		visited[x][y] = true;
		for (int[] dir : directions) {
			dfs(matrix, visited, x + dir[0], y + dir[1], matrix[x][y]);
		}
	}
}