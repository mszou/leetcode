/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */

public class Solution {
	// idea: DFS to find every island, when visiting a '1', search for continuous '1's in four directions
	// and change those '1's to '0's, then continue to look for next island.	O(mn) Time, O(1) Space.
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfsMarking(grid, i, j);	// mark all 1s in this island as 0s
					count++;
				}
			}
		}
		return count;
	}

	private void dfsMarking(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
			return;
		}
		grid[i][j] = '0';
		dfsMarking(grid, i + 1, j);
		dfsMarking(grid, i - 1, j);
		dfsMarking(grid, i, j + 1);
		dfsMarking(grid, i, j - 1);
	}
}