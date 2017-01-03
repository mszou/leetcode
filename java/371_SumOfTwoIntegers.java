/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example:
 * Given a = 1 and b = 2, return 3.
 */

public class Solution {
	public int getSum(int a, int b) {	// return a + b
		// idea: bit manipulation, using AND, XOR and shift
		// observe 0 + 0 = 0, 0 + 1 = 1, 1 + 0 = 1, 1 + 1 = 10
		//		   0 ^ 0 = 0, 0 ^ 1 = 1, 1 ^ 0 = 1, 1 ^ 1 = 0, but for AND, only 1 & 1 = 1
		// so sum: a + b == (a ^ b + c), where c = (a & b) << 1
		// Implementation: First, use "and" ("&") operation between a and b to find a carry = a & b.
		// Second, use "xor" ("^") operation between a and b to find the different bit, and assign it to a.
		// Then, shift carry one position left and assign it to b. Iterate until there is no carry (b == 0)
		if (a == 0) {
			return b;
		}
		while (b != 0) {
			int carry = a & b;	// only 1 & 1 = 1, 1 + 1 = 10 (left shift carry)
			a ^= b;
			b = carry << 1;
		}
		return a;
	}

	// follow-ups: bit manipulation can also be used in subtraction and multiplication

	public int getDifference(int a, int b) {	// return a - b
		if (a == 0) {
			return ~b + 1;
		}
		while (b != 0) {
			int borrow = (~a) & b;
			a ^= b;
			b = borrow << 1;
		}
		return a;
	}

	public int getProduct(int a, int b) {	// return a * b
		if (a == 0 || b == 0) {
			return 0;
		}
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = getSum(a, res);
			}
			a <<= 1;
			b >>>= 1;
		}
		return res;
	}
}