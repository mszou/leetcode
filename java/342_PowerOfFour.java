/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * Follow up: Could you solve it without loops/recursion?
 */

public class Solution {
	public boolean isPowerOfFour(int num) {
		// idea: power of four only have one '1' bit in their binary notation, and that 1 is located at odd position
		if (num <= 0) {
			return false;
		}
		return (num & (num - 1)) == 0 && (num & 0xAAAAAAAA) == 0;   // or use (num & 0x55555555) != 0
		// the right half is to eliminate numbers with '1' at even position, i.e. power of 2 but not power of 4
	}
}