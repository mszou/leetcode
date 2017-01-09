/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 */

public class Solution {
    public int uniquePaths(int m, int n) {
        // idea: 2D DP. ways[i][j] is # ways from 'Start' (grid[0,0]) to grid[i,j]
        // O(mn) Time, O(mn) Space.
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] ways = new int[m][n];
        // initilize: cells in first row or first column have only one way
        for (int i = 0; i < m; i++) {
            ways[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            ways[0][i] = 1;
        }
        // update rule: ways to get (i,j) = ways to get (i-1,j) + ways to get (i,j-1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
            }
        }
        return ways[m - 1][n - 1];
    }
}