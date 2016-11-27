/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * Example:
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 * Return 4.
 * The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 * After the rain, water are trapped between the blocks. The total volume of water trapped is 4.
 */

public class Solution {
	// idea: use min-heap. consider borders first because the lowest bar determines the height of the bukkit water.
	// Step: 1. Create Cell(row, col, height); 2. PriorityQueue on Cell of all 4 borders;
	// 3. Process each element in queue, and add surrounding blocks into queue.; 4. Mark checked block

	public class Cell {
		int row;
		int col;
		int height;
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}

	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
			return 0;
		}
		PriorityQueue<Cell> q = new PriorityQueue<>(1, new Comparator<Cell>() {
			public int compare(Cell a, Cell b) {
				return a.height - b.height;
			}
		});
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];
		// first add all the cells on the borders to the queue
		for (int i = 0; i < m; i++) {
			q.offer(new Cell(i, 0, heightMap[i][0]));
			visited[i][0] = true;
			q.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
			visited[i][n - 1] = true;
		}
		for (int i = 0; i < n; i++) {
			q.offer(new Cell(0, i, heightMap[0][i]));
			visited[0][i] = true;
			q.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
			visited[m - 1][i] = true;
		}
		// need to check the heights of neighbors in four directions
		int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int res = 0;
		while (!q.isEmpty()) {
			Cell cell = q.poll();	// pick the lowest bar
			for (int[] dir : directions) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heightMap[row][col]);
					q.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
				}
			}
		}
		return res;
	}
}