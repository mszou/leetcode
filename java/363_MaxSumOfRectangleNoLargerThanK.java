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
        // idea: DP. two pointers l & r, run Kadane's algo on a temp column storing row-wise sum of curr rectangle.
        // Kadane's algo takes O(m) time on array of length m, use TreeSet to find the maxSum <= k in O(logm) time.
        // move pointers O(n^2) time, so total O(n^2 * mlogm) Time (vs. brute-force O(m^2n^2) time), O(m) Space.
        // if n > m, can run on the other direction.    tutorial: https://www.youtube.com/watch?v=yCQN096CwWM
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        // we assume m > n, so outer loop use n (the smaller one), two pointers left & right
        for (int left = 0; left < n; left++) {  // O(n) loop
        	int[] sums = new int[m];   // temporary column of row-wise sums for current rectangle
        	for (int right = left; right < n; right++) {   // O(n) loop
        		for (int i = 0; i < m; i++) { // O(m)
        			sums[i] += matrix[i][right];	// calculate sums[]
        		}
        		TreeSet<Integer> set = new TreeSet<Integer>();    // use TreeSet to find maxSum <= k
        		set.add(0);   // case of subarray from 1st row: preSum = currSum - subarraySum = 0
        		int currSum = 0;  // accumulative sum, subarraySum = currSum - preSum
        		for (int sum : sums) {    // O(m)
        			currSum += sum;
        			// subarraySum = currSum - preSum, the smallest preSum >= currSum - k
        			// gives the largest (currSum - preSum) <= k, i.e. subarraySum <= k
        			Integer preSum = set.ceiling(currSum - k);   // O(logm)
        			if (preSum != null) {    // preSum exists in the TreeSet
        				res = Math.max(res, currSum - preSum);
                        if (res == k) { // cannot be even larger
                            return k;
                        }
        			}
        			set.add(currSum);    // add currSum as preSum for next sums
        		}
        	}
        }
        return res;
    }
}