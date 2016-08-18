/**
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 *
 * Example1:
 * a = 2
 * b = [3]
 * Result: 8
 *
 * Example2:
 * a = 2
 * b = [1,0]
 * Result: 1024
 */

// One knowledge: ab % k = (a%k)(b%k) % k. e.g. a^123 % k = [(a^120 % k)(a^3 % k)] % k = [((a^12 % k)^10 % k)(a^3 % k)] % k
// Suppose f(a,b) calculates a^b % k; Then f(a,123) = [f(a,120) * f(a,3)] % k = [f(f(a,12),10) * f(a,3)] % k;

public class Solution {
	public int base = 1337;

    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length, base);
    }

    private int superPow(int a, int[] b, int length, int k) {
    	if (length == 1) {
    		return powMod(a, b[0], k);
    	}
    	return powMod(superPow(a, b, length - 1, k), 10, k) * powMod(a, b[length - 1], k) % k;
    }

    // calculate x^y % k (y has only 1 digit)
    private int powMod(int x, int y, int k) {
    	x %= k;
    	int res = 1;
    	for (int i = 0; i < y; i++) {
    		res = (res * x) % k;
    	}
    	return res;
    }
}