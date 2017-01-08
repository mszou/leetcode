/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */

// dividend / divisor = quotient = 2^a1 + 2^a2 + ... + 2^an
// Integer.MIN_VALUE = -2147483648, Integer.MAX_VALUE = 2147483647

 public class Solution {
    public int divide(int dividend, int divisor) {
    	// idea: In essence, division is equivalent to finding how many times we can
    	// subtract the divisor from the dividend without making the dividend negative.
    	// quotient can be expressed as sum of polynomials of 2 (2^a1 + 2^a2 +...+ 2^an)
    	// use bit manipulation: shift the divisor to the left by i bit => divisor * 2^i
        if (divisor == 0) {
        	return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
        	return 0;
        }
        // Integer.MIN_VALUE = -2147483648, Integer.MAX_VALUE = 2147483647
        if (dividend == Integer.MIN_VALUE && divisor == -1) {   // may cause overflow
        	return Integer.MAX_VALUE;
        }
        // reduce the problem to positive long integers, use long to avoid overflow
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int quotient = 0;
        while (a >= b) {
        	int shift = 0;
        	while (a >= (b << shift)) {
        		shift++;
        	}
        	shift--;
        	a -= b << shift;   // subtract b * 2^shift from a
        	quotient += 1 << shift;
        }
        return isNegative ? -quotient : quotient;
    }
}