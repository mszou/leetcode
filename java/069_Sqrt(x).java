/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */

 public class Solution {
    public int mySqrt(int x) {
    	// idea: Binary Search, compare mid^2 and x, when computing square, use
        // division instead of multiplication to avoid overflow.    O(logn) Time.
        if (x <= 1) {   // for cases x = 0 and x = 1
        	return x;
        }
        int low = 1, high = x;
        while (low <= high) {
        	int mid = low + (high - low) / 2;
        	if (mid > x / mid) {	// mid^2 > x
        		high = mid - 1;
        	} else {
        		if (mid + 1 > x / (mid + 1)) {	// mid^2 <= x < (mid+1)^2
        			return mid;
        		} else {
                    low = mid + 1;
                }
        	}
        }
        return -1;
    }
}