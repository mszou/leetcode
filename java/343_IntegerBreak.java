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
        // idea: we need to do break and get a greater product only when n > 4. 
        // For n > 4, the maximum product should only contains factors 2 and 3, and 3 is preferred.
        // proof: 1. factor 1 makes no contribution to the product, so it is wasteful
        // 2. a factor f >= 4 can be replaced by 2 and f-2, then 2(f-2) = 2f-4 >= f. so we never need a factor >= 4. 
        // 3. for factors 2 and 3, since 3*3 > 2*2*2, we should not use 2 more than twice
        // corner cases: 2=1+1, 3=1+2, 4=2+2
        
        // for cases n == 2, 3, 4
        if (n < 4) {
        	return n - 1;  // 1 for 2, 2 for 3
        }
        if (n == 4) {
        	return 4;
        }
        int product = 1;
        while (n > 4) {
        	product *= 3;
        	n -= 3;
        }
        product *= n;
        return product;
    }
}