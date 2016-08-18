/**
 * Implement pow(x, n).
 */

public class Solution {
    public double myPow(double x, int n) {
    	// idea: divide and conquer + recurrsion
        // corner cases:
        if (n == 0) {
        	return 1;
        }
        if (n == 1) {
        	return x;
        }
        if (n == -1) {
        	return 1 / x;
        }
        double num = myPow(x, n / 2);
        return num * num * myPow(x, n % 2);
    }
}