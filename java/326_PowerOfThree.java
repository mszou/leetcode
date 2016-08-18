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
        // loop sol
        while (n % 3 == 0) {
        	n /= 3;
        }
        return (n == 1);

		// // follow-up sol: without using any loop / recursion
		// // 1162261467 is 3^19,  3^20 is bigger than Integer.MAX_VALUE
		// return (1162261467 % n == 0);
    }
}