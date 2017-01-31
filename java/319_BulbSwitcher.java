/**
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. 
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, 
 * you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 * Example:
 * Given n = 3. 
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off]. 
 * So you should return 1, because there is only one bulb is on.
 */

public class Solution {
    public int bulbSwitch(int n) {
        // A bulb is on in the end iff it is switched for an odd number of times, i.e. iff it has an odd number of
        // divisors. Since divisors come in pairs, except for perfect square numbers, we can infer that bulb i is
        // on after n rounds iff i is a perfect square number. Therefore, just count the square numbers in [1, n].
    	return (int) Math.sqrt(n);	// this is exactly # perfect square numbers <= n
    }	
}