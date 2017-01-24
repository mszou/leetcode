/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

public class Solution {
    public int maxProfit(int k, int[] prices) {
        // idea: 2D DP. dp[i][j] is the max profit for up to i transactions by time j (0<=i<=k, 0<=j<=T)
        // use a function quickSolve to tackle some corner cases to avoid TLE.
        int len = prices.length;
        if (k >= len / 2) {
        	return quickSolve(prices);
        }
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
        	int max = -prices[0];
        	for (int j = 1; j < len; j++) {
        		dp[i][j] = Math.max(dp[i][j - 1], prices[j] + max);
        		max = Math.max(max, dp[i - 1][j - 1] - prices[j]);
        	}
        }
        return dp[k][len-1];
    }

    private int quickSolve(int[] prices) {
    	int len = prices.length;
    	int profit = 0;
    	for (int i = 1; i < len; i++) {
    		// as long as there is a price increace, we gain a profit.
    		if (prices[i] > prices[i - 1]) {
    			profit += prices[i] - prices[i - 1];
    		}
    	}
    	return profit;
    }
}