/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note:
 * 0 ≤ x, y < 231.
 * Example:
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 */

public class Solution {
    public int hammingDistance(int x, int y) {
    	// idea: do XOR operation, then count the bits of '1' in the result.
    	int xor = x ^ y, count = 0;

    	// sol 1: simplest, use bitCount()
    	return Integer.bitCount(xor);

    	// sol 2: naive, check every bit
    	while (xor != 0) {
    		count += xor & 1;
    		xor >>= 1;
    	}
    	return count;

    	// sol 3: every time remove the right-most 1, using n & (n-1)
    	while (xor != 0) {
    		xor = xor & (xor - 1);
    		count++;
    	}
    	return count;

    	// reference: Several ways to count bits: 
    	// https://tech.liuchao.me/2016/11/count-bits-of-integer/
    }
}