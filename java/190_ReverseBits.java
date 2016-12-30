/**
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * Related problem: Reverse Integer
 */

public class Solution {
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		// idea: bit manipulation, each time append the right-most digit of n to res
		// use bit shift: right shift for n and left shift for res
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res <<= 1;	// spare 1 bit room for the upcoming digit
			res += n & 1;	// add the right-most digit of n
			n >>>= 1;	// unsigned bit shift, fill 0 into the blank space at left
		}
		return res;
	}
	// follow-up optimization if called many times, use a hashmap to store reversion of each 4-bit integer
	// divide the given integer into several 4-bit parts, reverse each part and then combine them
}