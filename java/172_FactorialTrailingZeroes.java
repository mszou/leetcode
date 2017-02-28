/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */

 public class Solution {
    public int trailingZeroes(int n) {
        // idea: the number of trailing zeroes depends on the number of 5's and 2's in the prime factorization
        // of n! because all trailing 0 is produced by factors 5 * 2. Since factors 2 is always ample, we just
        // count the factor 5. Note that some numbers may have several 5 factors, e.g. 25 have two 5 factors,
        // and 125 have three 5 factors, so we need to count the total # 5 factors in all numbers from 1 to n.

        // // sol 1: recursive
        // return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);

        // sol 2: non-recursive
        if (n < 5) {
        	return 0;
        }
        // compute the max power of 5 that is <= n (k = log_5(n), n >= 5^k)
        int k = (int) (Math.log(n) / Math.log(5));
        int sum = 0;
        // a number having i factor 5 will be counted in i rounds regarding 5^i, 5^(i-1),... to 5
        while (k > 0) {
        	sum += n / Math.pow(5, k--);
        }
        return sum;
    }
}