/**
 * Given an integer, write a function to determine if it is a power of three.
 * Follow up:
 * Could you do it without using any loop / recursion?
 */

public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
        	return false;
        }

        // loop sol: iteratively divide n by 3, check whether end up with 1.
        while (n % 3 == 0) {
        	n /= 3;
        }
        return (n == 1);

		// // follow-up sol: without using any loop / recursion
        // // find the largest power of 3 within the range of Integer, check whether it can
		// // be divided by n.    3^19 is 1162261467, 3^20 is bigger than Integer.MAX_VALUE
		// return (1162261467 % n == 0);
    }
}