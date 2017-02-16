/**
 *	You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */

public class Solution {
	// idea: BFS (better) or DFS
	// sol 1: BFS from all gates together using a queue, update all reachable empty rooms. Each time we
	// poll a cell from queue, update its neighbors and add them into queue.	O(mn) Time, O(mn) Space.
	public void wallsAndGates(int[][] rooms) {
		if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
			return;
		}
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == 0) {	// put all gates into queue
					queue.offer(new int[]{i, j});
				}
			}
		}
		int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};	// 4 directions
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int[] dir : directions) {
				int row = curr[0] + dir[0];
				int col = curr[1] + dir[1];
				// skip cases of beyond range or not a empty room (wall or visited room)
				if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || rooms[row][col] != Integer.MAX_VALUE) {
					continue;
				}
				rooms[row][col] = rooms[curr[0]][curr[1]] + 1;
				queue.offer(new int[]{row, col});
			}
		}
	}

	// sol 2: do DFS from gate, update rooms that it can reach within a smaller distance
	public void wallsAndGates(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == 0) {
					dfs(rooms, i, j, 0);
				}
			}
		}
	}

	private void dfs(int[][] rooms, int i, int j, int distance) {
		if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) {
			return;
		}
		rooms[i][j] = d;
		dfs(rooms, i - 1, j, d + 1);
		dfs(rooms, i + 1, j, d + 1);
		dfs(rooms, i, j - 1, d + 1);
		dfs(rooms, i, j + 1, d + 1);
	}
}