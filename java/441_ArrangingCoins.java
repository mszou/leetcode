/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * Example 1:
 * n = 5
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * Because the 3rd row is incomplete, we return 2.
 * Example 2:
 * n = 8
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * Because the 4th row is incomplete, we return 3.
 */

public class Solution {
	public int arrangeCoins(int n) {
		// idea: Math, O(1) Time. k complete rows require n = (1+k)*k/2 coins
		// --> (2k+1)(2k+1) = 8n+1， so we can get k = (sqrt(8n+1)-1)/2
		return (int)((Math.sqrt(1 + 8 * (long)n) - 1) / 2);

		// sol 2: Binary search, O(logn) Time.
		int start = 0;
		long end = n, mid;	// avoid integer overflow
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (0.5 * mid * (mid + 1) <= n) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start - 1;
	}
}