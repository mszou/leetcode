/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * Example:
 * Given matrix = [
 *   [1,  0, 1],
 *   [0, -2, 3]
 * ]
 * k = 2
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 */

public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 2D Kadane's algorithm (to find maximum sum rectangle in 2D matrix) + bound k
        // tutorial: https://www.youtube.com/watch?v=yCQN096CwWM
        // use TreeSet to find the rectangle with maxSum <= k within O(logm) time
        // suppose m > n, O(n^2 * mlogm) Time (brute-force O(m^2n^2)), O(m) Space
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        // we assume m > n, so outer loop use n (the smaller one)
        for (int left = 0; left < n; left++) {
        	// a temporary array to accumulate sums for current rectangle
        	int[] sums = new int[m];
        	for (int right = left; right < n; right++) {
        		for (int i = 0; i < m; i++) {
        			sums[i] += matrix[i][right];	// update sums[]
        		}
        		// use TreeSet to find the rectangle with maxSum <= k within O(logm) time
        		TreeSet<Integer> set = new TreeSet<Integer>();
        		// add 0 to cover the single row case
        		set.add(0);
        		int currSum = 0;
        		for (int sum : sums) {
        			currSum += sum;
        			// use sum subtraction (currSum - sum) to get the subarray with sum <= k
        			// therefore we need to look for the smallest sum >= currSum - k
        			Integer num = set.ceiling(currSum - k);
        			if (num != null) {
        				res = Math.max(res, currSum - num);
        			}
        			set.add(currSum);
        		}
        	}
        }
        return res;
    }
}