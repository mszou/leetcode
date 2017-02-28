/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * In this case, no transaction is done, i.e. max profit = 0.
 */

public class Solution {
    public int maxProfit(int[] prices) {
    	// idea: only one transaction, so find the greatest ascending difference. Traverse the
        // array, record the min price so far and max profit so far.   O(n) Time, O(1) Space.
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;    // record the minimal price so far
        int profit = 0; // record the maximal profit so far
        for (int curr : prices) {
            min = Math.min(curr, min);
            profit = Math.max(profit, curr - min);   // update, if find a greater difference
        }
        return profit;
    }
}