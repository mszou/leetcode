/**
 * Implement pow(x, n).
 */

public class Solution {
	public double myPow(double x, int n) {
		// idea: divide and conquer + recurrsion
		// corner cases: x^0 = 1, x^1 = x, x^(-1) = 1 / x, 1^n = 1, 0^n = 0
		if (n == 0 || x == 1) {
			return 1;
		}
		if (n == 1 || x == 0) {
			return x;
		}
		if (n == -1) {
			return 1 / x;
		}
		double num = myPow(x, n / 2);
		return num * num * myPow(x, n % 2);
	}
}