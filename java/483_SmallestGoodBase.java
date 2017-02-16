/**
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 * Now given a string representing n, you should return the smallest good base of n in string format. 
 * Example 1:
 * Input: "13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 * Example 2:
 * Input: "4681"
 * Output: "8"
 * Explanation: 4681 base 8 is 11111.
 * Example 3:
 * Input: "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 * Note:
 * The range of n is [3, 10^18].
 * The string representing n is always valid and will not have leading zeros.
 */

/* 13 base 3 is 111 because 13 = 1 + 3^1 + 3^2 */
/* If num = "1111...11" length k under base b,
num = 1 + b + b^2 + ... + b^(k-1) = (b^k - 1) / (b-1) > b^(k-1) */
public class Solution {
	// idea: Binary Search. To find the smallest base means to find the longest possible all-1
	// representation based on k. As n <= 10^18, so n < (1 << 62). We iterate the length of the
	// representation from 62 to 2 (2 can always be valid, with base = n-1), and check whether
	// a given length can be valid. Then for a given length p, use binary search to check whether
	// there is a base k that satisfies 1+k+k^2+...+k^p = num, k in [1, pow(n, 1/p) + 1].
	public String smallestGoodBase(String n) {
		long num = 0;
		for (char c : n.toCharArray()) {
			num = num * 10 + c - '0';
		}
		long x = 1;
		for (int p = 62; p >= 1; p--) {
			if ((x << p) < num) {
				long k = helper(num, p);
				if (k != -1) {
					return String.valueOf(k);
				}
			}
		}
		return String.valueOf(num - 1);
	}

	private long helper(long num, int p) {
		long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
		while (l < r) {
			long mid = l + (r - l) / 2;
			long sum = 0, cur = 1;
			for (int i = 0; i <= p; i++) {
				sum += cur;
				cur *= mid;
			}
			if (sum == num) {
				return mid;
			} else if (sum > num) {
				r = mid;
			} else l = mid + 1;
		}
		return -1;
	}
}