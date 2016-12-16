/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */

public class Solution {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		// idea: bit shifting, unsigned right shift
		int count = 0;

		// // sol 1: right shift one bit at a time, check if the last bit is 1
		// while (n != 0) {
		// 	count += n & 1;
		// 	n >>>= 1;
		// }

		// sol 2: x & (x-1) helps to remove right most 1 for x, more efficient
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}
}