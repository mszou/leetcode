/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
 * Example 1:
 * Input:
 * 3
 * Output:
 * 3
 * Example 2:
 * Input:
 * 11
 * Output:
 * 0
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */

public class Solution {
	public int findNthDigit(int n) {
		// idea: there are 9 one-digit numbers, 90 two-digit numbers, 900 three-digit numbers, etc
		// so if n in [1,9], it's a 1-digit num, [10,189] -> 2-digit num, [190,2889] -> 3-digit num 
		// find the range where n lies, then find that number and then the exact digit
		int len = 1, base = 1;	// len is the length of number, base is the first num in this level
		long count = 9;
		while (n > len * count) {
			n -= len * count;
			len += 1;
			base *= 10;
			count *= 10;
		}
		// when while loop ends, n is the offset in current level (the range starting with base)
		int num = (n - 1) / len + base;	// find the number containing that digit
		String s = Integer.toString(num);
		return Character.getNumericValue(s.charAt((n - 1) % len));
	}
}