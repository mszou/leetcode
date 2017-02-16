/**
 * Given an integer, write a function to determine if it is a power of two.
 */

public class Solution {
    public boolean isPowerOfTwo(int n) {
    	if (n <= 0) {
            return false;
        }
        
        // // sol 1: recursive. Time Complexity: O(log n)
        // if (n == 1) {
        //     return true;
        // }
        // if (n % 2 != 0) {
        // 	return false;
        // }
        // return isPowerOfTwo(n / 2);
        
        // // sol 2: iterative. Time complexity: O(log n)
        // while (n % 2 == 0) {
        //     n = n / 2;
        // }
        // return (n == 1);
        
        // sol 3: bit manipulation (complementary bits). A power of 2 has only one bit
        // of '1' in its binary representation, this can be checked by n & (n-1) == 0
        return (n & (n-1)) == 0;
    }
}