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

// knowledge: ab % k = (a % k)(b % k) % k. e.g. a^123 % k = [(a^120 % k)(a^3 % k)] % k = [((a^12 % k)^10 % k)(a^3 % k)] % k
// Suppose f(a,b) calculates a^b % k; Then f(a,123) = [f(a,120) * f(a,3)] % k = [f(f(a,12),10) * f(a,3)] % k;
// so for a^b % 1337, we divide b into several single-digit number and then compute.    O(b.length) Time.
public class Solution {
	public int base = 1337;

    // sol 1: recursive
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


    // sol 2: iterative
    public int superPow(int a, int[] b) {
        int n = b.length;
        int res = 1;
        for (int i = n - 1; i >= 0; i--) {
            res = (res * modPower(a, b[i])) % base;
            a = modPower(a, 10);
        }
        return res;
    }

    private int modPower(int a, int e) {    // computes a^e % base
        int res = 1;
        a = a % base;
        while (e > 0) {
            if ((e & 1) != 0) {
                res = (res * a) % base;
            }
            e >>= 1;
            a = (a * a) % base;
        }
        return res;
    }
}