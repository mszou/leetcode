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
		// idea: n/2 is faster to reach, so we should try to get more intermediate even numbers, i.e.
		// for an odd n, use +1 or -1 to make it multiple of 4 because it's better than multiple of 2.
		// If n ends with 01, then replace n with n-1; if n ends with 11, then replace n with n+1.
		// note that for case n = 3, n-1 is better than n+1. and be careful about Integer.MAX_VALUE
		int count = 0;
		if (n == Integer.MAX_VALUE) {	// 2^31-1 is all 1, but '+1' will make it overflow
			return 32;	// a 'n+1' and 31 'n/2'
		}
		while (n > 1) {
			if ((n & 1) == 0) {	// n is even
				n >>>= 1;	// do 'n/2'
			} else {
				if (n == 3 || (n & 2) == 0) {	// n == 3 or n ends with '01'
					n--;	// do 'n-1'
				} else {	// n ends with '11' and n != 3
					n++;	// do 'n+1'
				}
			}
			count++;
		}
		return count;
	}
}