/**
 * Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.
 * Notice
 * It's guaranteed that the size of the array is greater or equal to k.
 * Example
 * Given nums = [1, 12, -5, -6, 50, 3], k = 3
 * Return 15.667 // (-6 + 50 + 3) / 3 = 15.667
 */

public class Solution {
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // idea: Binary search for the answer. First find the min & max in nums as the lower and higher bounds.
    	// We try mid as avg and analyze the differences num[i] - mid, use an array to store the accumulative
    	// sum of difference, traverse nums to update sums and record the min sum k pos befrore current pos,
    	// once we find a sum[i] >= min_pre, it means there is a subarray whose avg >= mid, then we move lower
    	// bound to mid, otherwise move higher bound to mid. Repeat this until two bounds meet (close enough).
        if (k <= 0 || nums == null || nums.length < k) {
        	return 0.0;
        }
        double lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        int n = nums.length;
        for (int num : nums) {
        	lo = Math.min(lo, num);
        	hi = Math.max(hi, num);
        }
        double[] sum = new double[n + 1];
        sum[0] = 0;
        while (hi - lo >= 1e-6) {	// cannot directly use '==' for doubles
        	double mid = lo + (hi - lo) / 2.0;
        	double minPreSum = 0;
        	boolean check = false;	// flag to show if current mid is small
        	for (int i = 1; i <= n; i++) {
        		sum[i] = sum[i - 1] + nums[i - 1] - mid;
        		if (i >= k && sum[i] >= minPreSum) {
        			check = true;
        			break;
        		}
        		if (i >= k) {
        			minPreSum = Math.min(minPreSum, sum[i - k + 1]);
        		}
        	}
        	if (check) {
        		lo = mid;
        	} else {
        		hi = mid;
        	}
        }
        return lo;
    }
}