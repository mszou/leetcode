/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */

public class Solution {
	// idea: Traverse the matrix, For each building, use BFS to compute the shortest distance from each '0' to this building.
	// Then choose the position of 0 with shortest distance sum among all positions that can reach all the buildings.
	// Time: O(# 0)O(#1) ~ O(m^2n^2)
	public int shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		final int[][] directions = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
		int m = grid.length, n = grid[0].length;
		int[][] distance = new int[m][n];	// record the distance sum to all buildings it can reach
		int[][] reach = new int[m][n];	// record # buildings this position can reach
		int buildingNum = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					buildingNum++;
					Queue<int[]> queue = new LinkedList<int[]>();
					queue.offer(new int[]{i,j});
					boolean[][] visited = new boolean[m][n];
					int level = 1;
					while (!queue.isEmpty()) {
						int qSize = queue.size();
						for (int q = 0; q < qSize; q++) {
							int[] curr = queue.poll();
							for (int[] dir : directions) {
								int row = curr[0] + dir[0];
								int col = curr[1] + dir[1];
								if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
									continue;
								}
								// the shortest distance from [row][col] to this building is 'level'
								distance[row][col] += level;
								reach[row][col]++;
								visited[row][col] = true;
								queue.offer(new int[]{row, col});
							}
						}
						level++;
					}
				}
			}
		}
		int shortest = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
					shortest = Math.min(shortest, distance[i][j]);
				}
			}
		}
		return shortest == Integer.MAX_VALUE ? -1 : shortest;
	}
}