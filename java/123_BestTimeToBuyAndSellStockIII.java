/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

public class Solution {
    public int maxProfit(int[] prices) {
        // idea: DP. consider the left-hand side & right-hand side separately
        // left[i]/right[i] records the maximum profit on the left-hand/right-hand side of prices[i]
        // if at most k transactions allowed, see: http://www.jiuzhang.com/solutions/best-time-to-buy-and-sell-stock-iv/
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);     // minimal price on the left-hand side
            left[i] = Math.max(left[i - 1], prices[i] - min);   // maximal profit on the left-hand side
        }
        
        // DP from right to left;
        right[len-1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);     // maximal price on the right-hand side
            right[i] = Math.max(right[i + 1], max - prices[i]); // maximal profit on the right-hand side
        }
        
        int profit = 0;
        for (int i = 0; i < prices.length; i++){	// two transactions separated at position i
            profit = Math.max(left[i] + right[i], profit);	// find the maximum profits via two transactions
        }
        return profit;
    }
}