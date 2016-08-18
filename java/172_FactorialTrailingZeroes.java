/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */

 public class Solution {
    public int trailingZeroes(int n) {
        // idea: the number of trailing zeroes depends on the number of 5 in the prime factorization of n!
        // Because all trailing 0 is from factors 5 * 2. But sometimes one number may have several 5 factors, 
        // for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is 
        // always ample. So we just count how many 5 factors in all number from 1 to n.

        // // sol 1: recursion
        // return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);

        // sol 2: non-recursion
        if (n < 5) {
        	return 0;
        }
        int k = (int) (Math.log(n) / Math.log(5));
        int sum = 0;
        while (k > 0) {
        	sum += n / Math.pow(5, k--);
        }
        return sum;
    }
}