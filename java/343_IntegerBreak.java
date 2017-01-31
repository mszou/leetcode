/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize 
 * the product of those integers. Return the maximum product you can get.
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * Hint:
 * There is a simple O(n) solution to this problem.
 * You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 */

public class Solution {
    public int integerBreak(int n) {
        // idea: only when n > 4 we can get a greater product by breaking. And for n > 4, the maximum
        // product should only contains factors 2 and 3, and 3 is preferred.    O(n) Time, O(1) Space.
        // proof: 1. factor 1 makes no contribution to the product, so it is wasteful.
        // 2. a factor f>=4 can be replaced by 2 and f-2. 2(f-2) = 2f-4 >= f. so never need a factor >= 4. 
        // 3. for 2 and 3, 3*3 > 2*2*2, so using 3 twice is better than using 2 three times.
        // so continuously take 3's from n as long as n > 4 and take the final remainder as the last.
        
        // for corner cases: n == 2 (1+1), 3 (1+2), 4 (2+2)
        if (n < 4) {
        	return n - 1;  // actually is 1 * (n-1), i.e. 1 for 2, 2 for 3
        }
        if (n == 4) {
        	return 4;
        }
        int product = 1;
        while (n > 4) { // O(n/3) Time
        	product *= 3;
        	n -= 3;
        }
        product *= n;
        return product;
    }
}