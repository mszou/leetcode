/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * Example:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */

public class Solution {
	public int islandPerimeter(int[][] grid) {
		// idea: every island has 4 edges, but edges between adjacent island cells are not in the
		// perimeter, so perimeter = islands * 4 - neighbors * 2.	O(mn) Time, O(1) Space.
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int islands = 0, neighbors = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					islands++;
					if (i < grid.length - 1 && grid[i + 1][j] == 1) {
						neighbors++;	// has a downward neighbor
					}
					if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
						neighbors++;	// has a right neighbor
					}
				}
			}
		}
		return 4 * islands - 2 * neighbors;
	}
}