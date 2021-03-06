/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

public class Solution {
    public int maxProfit(int[] prices) {
    	// idea: DP. You have 3 coices for each day: buy(hold), sell, rest. Since you can not be engaged in 
    	// multiple trasactions, use sell/hold/rest to record the maxProfit so far end with sell/hold/rest
        if (prices.length < 2) {
        	return 0;
        }
        int sell = 0, hold = Integer.MIN_VALUE, rest = 0, prev_sell;
    	for (int price : prices) {
        	prev_sell = sell;
        	sell = hold + price;
        	hold = Math.max(hold, rest - price);
        	rest = Math.max(rest, prev_sell);
    	}
    	return Math.max(sell, rest);
    }
}