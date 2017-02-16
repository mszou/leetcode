/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * Consider the following matrix:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // idea: Compare the number at the top right corner with target. if it < target, then we can rule out
        // the first row; if it > target, then rule out the last column; if it == target, then we find target.
        // Therefore, we can get the result within at most (m + n) comparisons. So O(m+n) Time, O(1) Space.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        int row = 0, col = matrix[0].length - 1;	// start from the top right corner
        // int count = 0;	// if need to count the occurrence of target
        while (col >= 0 && row <= matrix.length - 1) {
        	if (matrix[row][col] == target) {
        		return true;
        		/* count++;
        		row++;
        		col--; */ //if need to count the occurrence of target
        	} else if (matrix[row][col] < target) {
        		row++;
        	} else {
        		col--;
        	}
        }
        return false;
    }
}