/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example:
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 * Challenge:
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

public class Solution {
	// idea: Union find. use trees to represent a list of islands. roots[c] = p means p is
	// the parent of c. Do roots[root[...roots[c]]] can trace to the root of their island.
	int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> res = new ArrayList<>();
		if (m <= 0 || n <= 0) {
			return res;
		}
		int count = 0;	// number of islands
		int[] roots = new int[m * n];
		Arrays.fill(roots, -1);
		for (int[] p : positions) {
			int root = n * p[0] + p[1];	// turn 2-D position to 1-D index
			roots[root] = root;	// add new island as an isolated island
			count++;
			for (int[] dir : directions) {
				int x = p[0] + dir[0];
				int y = p[1] + dir[1];
				int nb = n * x + y;	// neighbor
				if (x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) {
					continue;
				}
				int rootNb = findIsland(roots, nb);
				if (root != rootNb) {	// neighbor is in another island
					roots[root] = rootNb;	// union two islands
					root = rootNb;	// current tree root = joined tree root
					count--;
				}
			}
			res.add(count);
		}
		return res;
	}

	public int findIsland(int[] roots, int id) {
		while (id != roots[id]) {
			id = roots[id];
		}
		return id;
	}
}