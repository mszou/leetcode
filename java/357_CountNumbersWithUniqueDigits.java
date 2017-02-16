/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * Hint:
 * 1. A direct way is to use the backtracking approach.
 * 2. Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number 
 * is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
 * 3. This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 * 4. Let f(k) = count of numbers with unique digits with length equals k.
 * f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 */


public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        // sol 0: Actually there are only 11 different answers, for n from 0 to 10 (the answer for n>10 is the same as n=10).
        // You can just create a lookup table. Then this problem becomes O(1) Time in essence.
        
        // sol 1: Math (Maybe also DP). consider putting digits into each position, 0 cannot be put in the first position
        // when n = 1, _ can hold any one from [0,...,9], so there are 10 numbers among one-digit numbers (0~9);
        // when n = 2, _ _ first position has 9 choices (1~9) and second position has 9 choices (0~9 excluding first one).
        // There are 9*9=81 numbers with unique digits among 90 two-digit numbers (10~99). So when n = 2, return 10+81=91.
        // ... For i-digit numbers (2<=i<=10), there are 9*9*8*...*(11-i) numbers with unique digits.
        // No more eligible numbers among i-digit numbers when i > 10, so the result for n > 10 is same as res for n = 10.
        if (n == 0) {
        	return 1;	// got this answer from the test case
        }
        int res = 10;	// answer for single-digit numbers
        int base = 9;	// the base for the multiplication of each level, here 9 choices for the fist digit
        int available = 9;  // available digits left for rest positions
        n = Math.min(n, 10);
        for (int i = 2; i <= n; i++) {
        	base *= available;
        	res += base;
        	available--;
        }
        return res;
        
        
//         // sol 2: back-tracking. running time O(10!) or O(n!) if n < 10.
//         // idea: append one digit at a time recursively (only append digits that has not been appended before).
//         if (n > 10) {
// 			return countNumbersWithUniqueDigits(10);
// 		}
// 		int count = 1; // x == 0
// 		long max = (long) Math.pow(10, n);

// 		boolean[] used = new boolean[10];

// 		for (int i = 1; i < 10; i++) {
// 			used[i] = true;
// 			count += search(i, max, used);
// 			used[i] = false;
// 		}

// 		return count;
// 	}

// 	private static int search(long prev, long max, boolean[] used) {
// 		int count = 0;
// 		if (prev < max) {
// 			count += 1;
// 		} else {
// 			return count;
// 		}

// 		for (int i = 0; i < 10; i++) {
// 			if (!used[i]) {
// 				used[i] = true;
// 				long cur = 10 * prev + i;
// 				count += search(cur, max, used);
// 				used[i] = false;
// 			}
// 		}

// 		return count;
    }
}