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
        
        // sol 3: non-recursive (complementary bits)
        // idea: Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case
        return (n & (n-1)) == 0;
    }
}