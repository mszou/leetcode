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
	// idea: BFS. create a Cell class with row, col, height. use a min-heap on Cell to keep the lowest cell
	// start from the lowest bar on the border, do BFS in 4 directions and accumulate the differences in
	// height if find lower cells. Process cell in queue, and add unvisited surrounding cells into the queue.

	public class Cell implements Comparable<Cell> {
		int row;
		int col;
		int height;
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}

		public int compareTo(Cell o) {
            return this.height - o.height;
        }
	}

	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
			return 0;
		}
		PriorityQueue<Cell> pq = new PriorityQueue<>(1);	// only keep the lowest cell visited so far
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];
		// first add all the cells on the borders to the queue
		for (int i = 0; i < m; i++) {
			pq.offer(new Cell(i, 0, heightMap[i][0]));
			visited[i][0] = true;
			pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
			visited[i][n - 1] = true;
		}
		for (int i = 0; i < n; i++) {
			pq.offer(new Cell(0, i, heightMap[0][i]));
			visited[0][i] = true;
			pq.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
			visited[m - 1][i] = true;
		}
		// four directions for BFS
		int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int res = 0;
		while (!pq.isEmpty()) {
			Cell cell = pq.poll();	// pick the lowest bar
			for (int[] dir : directions) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heightMap[row][col]);
					int newHeightBound = Math.max(heightMap[row][col], cell.height);
					pq.offer(new Cell(row, col, newHeightBound));
				}
			}
		}
		return res;
	}
}