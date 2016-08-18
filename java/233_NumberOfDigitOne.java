/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 * Hint: Beware of overflow.
 */

public class Solution {
    public int countDigitOne(int n) {
        // Go through the digit positions one at a time, find out how often a "1" appears at each position, and sum those up.
        // For each position, split the the number into two parts, for example split n=3141592 into a=3141 and b=592 when 
        // considering the thousands-digit. Then we know that the thousands-digit of n is 1 for prefixes "" to "314", i.e.,
        // 315 times. Each of those times is a streak of 1000 long. So (a / 10 + 1) * 1000 times, the hundreds-digit is 1.
        // However, since the thousands-digit is a 1, the very last streak isn't 1000 numbers but only 593 numbers, for the 
        // suffixes "000" to "592". So (a / 10 * 1000) + (b + 1) times, the thousands-digit is 1.
        // The case distincton between the current digit/position being 0, 1 and >=2 can easily be done in one expression. 
        // With (a + 8) / 10 you get the number of full streaks, and a % 10 == 1 tells you whether to add a partial streak.
        if (n <= 0) {
            return 0;
        }
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) { // use the long type to address overflow
        	long a = n / m, b = n % m;
        	ones += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return ones;
    }
}