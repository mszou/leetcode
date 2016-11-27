/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
    	// idea: DP. a super ugly number must be multiplied by one of the given primes from a smaller super ugly number.
        int[] times = new int[primes.length];	// record the index of ugly number to be multiplied for each given prime
        int[] uglys = new int[n];	// uglys[i] is the (i+1)th super ugly number
        uglys[0] = 1;
        for (int i = 1; i < n; i++) {
        	int min = Integer.MAX_VALUE;
        	// find the next super ugly number (smallest among the coming multiples)
        	for (int j = 0; j < primes.length; j++) {
        		min = Math.min(min, primes[j] * uglys[times[j]]);
        	}
        	uglys[i] = min;
        	// update the corresponding times[j]
        	for (int j = 0; j < times.length; j++) {
        		if (uglys[times[j]] * primes[j] == min) {
        			times[j]++;
        		}
        	}
        }
        return uglys[n - 1];
    }
}