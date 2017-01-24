/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 */

public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
		// idea: bit manipulation & shift. A bit in m & n is 1 only when both m & n have 1 at that bit.
		// So if m == n, res = m; when m != n, there are at least an odd num and an even num (different
		// in last bit), so last bit is 0. Similar for rest bits, so right shift m, n until they equal.
		if (m == 0) {
			return 0;
		}
		if (m == n) {
			return m;
		}
		int shiftBits = 0;
		while (m != n) {	// right shift until m == n
			m >>= 1;
			n >>= 1;
			shiftBits++;
		}
		return m << shiftBits;
	}
}