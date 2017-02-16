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
    	// idea: DP. A super ugly number (> 1) must be the product of a smaller super ugly number and a given prime.
        // Record the indexes of prior super ugly numbers that each prime is going to multiply next, find the smallest
        // among those products as next super ugly number, then update the indices.     O(mn) Time, O(m+n) Space.
        int[] times = new int[primes.length];	// records the indexes of number to be multiplied for each given prime
        int[] uglys = new int[n];	// uglys[i] is the (i+1)th super ugly number
        uglys[0] = 1;   // 1 is the first super ugly number
        for (int i = 1; i < n; i++) {   // find all n super ugly numbers
        	int min = Integer.MAX_VALUE;
        	// find the next super ugly number (smallest among the upcoming products)
        	for (int j = 0; j < primes.length; j++) {
        		min = Math.min(min, primes[j] * uglys[times[j]]);
        	}
        	uglys[i] = min;
        	// update the times array, might have more than one index should be updated
        	for (int j = 0; j < times.length; j++) {
        		if (uglys[times[j]] * primes[j] == min) {
        			times[j]++;
        		}
        	}
        }
        return uglys[n - 1];
    }
}