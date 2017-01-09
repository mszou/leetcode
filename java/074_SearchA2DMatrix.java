/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // sol 1: Binary Search Twice. first compare the heads of each row to find the row index,
        // then second binary search in this row to find the target.    O(logm + logn) Time.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        // 1st Binary Search to find the row index 
        int start = 0, end = m - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }   // ends with start == end + 1
        int rowIdx;
        if (end >= 0 && matrix[end][0] <= target) {
            rowIdx = end;
        } else if (start < m && matrix[start][0] <= target) {
            rowIdx = start;
        } else {    // target out of the range
            return false;
        }
        // 2nd Binary Search to find the target in the selected row
        start = 0;
        end = n - 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (matrix[rowIdx][mid] == target) {
                return true;
            } else if (matrix[rowIdx][mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;

        // sol 2: treat the matrix as a sorted array of length m*n, and Binary Search
        // when m*n is large, may cause overflow.  O(log(mn)) Time = O(logm + logn)
    }
}