/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 * Input: 16
 * Returns: True
 *
 * Example 2:
 * Input: 14
 * Returns: False
 */

public class Solution {
    public boolean isPerfectSquare(int num) {
    	// sol 1: binary search, similar to Problem 69. Sqrt(x). Time O(log n)
        // need to use long instead of int to address the Time Limit Exceeded problem for large int input
        if (num == 1) {
        	return true;
        }
        long low = 1;
        long high = num / 2;
        long numLong = (long) num;
        while (low <= high) {
        	long mid = low + (high - low) / 2;
        	if (mid * mid == numLong) {
        		return true;
        	} else if (mid * mid < numLong) {
        		low = mid + 1;
        	} else {
        		high = mid - 1;
        	}
        }
        return false;
        
        // // sol 2: Math. all perfect square numbers can be expressed as 1+3+5+...+(2n-1) = (2n-1+1)*n/2 = n^2
        // // Time O(n^0.5), worse than binary search, which is O(log n)
        // long i = 1, temp = 1;
        // long numLong = (long) num;
        // while (temp < numLong) {
        //     i += 2;
        //     temp += i;
        // }
        // return temp == numLong;
    }
}