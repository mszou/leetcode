/**
 * Implement pow(x, n).
 */

public class Solution {
	public double myPow(double x, int n) {
		// idea: divide and conquer, recursively compute pow(x, n/2) and multiply them.
		// O(logn) Time. Corner cases: x^0 = 1, x^1 = x, x^(-1) = 1/x, 1^n = 1, 0^n = 0
		if (n == 0 || x == 1) {
			return 1;
		}
		if (n == 1 || x == 0) {
			return x;
		}
		if (n == -1) {
			return 1 / x;
		}
		// version 1: double myPow
		double num = myPow(x, n / 2);
		return num * num * myPow(x, n % 2);
		// // version 2: double x
		// return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
	}
}