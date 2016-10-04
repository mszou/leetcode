/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 */

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        // idea: DP solution proceeds row by row. 3 arrays needed: height[j] is the cumulative number of
        // 1's so far in column j, left[j]/right[j] is the left/right index of current all-1 rectangle.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int left[] = new int[n], right[] = new int[n], height[] = new int[n];
        Arrays.fill(right, n);   // let right all n, left and height have values 0 by default
        int maxA = 0;   // area of rectangle so far
        // for each row
        for (int i = 0; i < m; i++) {   
            int cur_left = 0, cur_right = n - 1;
            // compute height (can do this from either side): the cumulative number of 1's in this column
            for (int j = 0; j < n; j++) {   
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // compute left (from left to right): the left index of current all-one rectangle
            for (int j = 0; j < n; j++) {   
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = 0; 
                    cur_left = j + 1;
                }
            }
            // compute right (from right to left): the right index of current all-one rectangle
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                  right[j]=Math.min(right[j],cur_right);  
                } else {
                    right[j] = n - 1; 
                    cur_right = j - 1;
                }
            }
            // compute the area of rectangle (can do this from either side)
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return maxA;
    }
}