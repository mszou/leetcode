/**
 * Given a positive integer n and you can do operations as follow:
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 * Example 1:
 * Input:
 * 8
 * Output:
 * 3
 * Explanation:
 * 8 -> 4 -> 2 -> 1
 * Example 2:
 * Input:
 * 7
 * Output:
 * 4
 * Explanation:
 * 7 -> 8 -> 4 -> 2 -> 1
 * or
 * 7 -> 6 -> 3 -> 2 -> 1
 */

public class Solution {
	public int integerReplacement(int n) {
		// idea: To get 1 ASAP, n/2 is faster, so try to get more intermediate even numbers
		// i.e. for an odd number, using +1 or -1 depends on whether the bit before last bit is 0 or 1
		// we can always replace the odd n with a number ending with 00 by +1 or -1
		// note that for case n = 3, n - 1 is better than n + 1, and be careful about Integer.MAX_VALUE
		int count = 0;
		if (n == Integer.MAX_VALUE) {	// Integer.MAX_VALUE is all 1, but cannot actually increment n
			return 32;
		}
		while (n > 1) {
			if ((n & 1) == 0) {	// even
				n >>>= 1;
			} else {
				if (n == 3 || (n & 2) == 0) {	// n == 3 or n ends with 01
					n--;
				} else {	// n ends with 11 and n != 3
					n++;
				}
			}
			count++;
		}
		return count;
	}
}