/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * Example:
 * n = 10, I pick 8.
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * Game over. 8 is the number I picked.
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 * Hint:
 * 1. The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
 * 2. Take a small example (n = 3). What do you end up paying in the worst case?
 * 3. Check out this article if you're still stuck.
 * 4. The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
 * 5. As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?
 */

public class Solution {
    // idea: 2D-DP.
	// sol 1: iterative, table[i][j] is the min $ to guarantee win for sub-problem: range=[i,j]
    public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        for (int j = 2; j <= n; j++) {
        	for (int i = j - 1; i > 0; i--) {
        		int min = Integer.MAX_VALUE;
        		for (int k = i + 1; k < j; k++) {
        			// compute the cost for guessing k
        			int temp = k + Math.max(table[i][k-1], table[k+1][j]);
        			min = Math.min(min, temp);	// guess k or not
        		}
        		table[i][j] = (i + 1 == j) ? i : min;
        // 		System.out.println("table[" + i + "][" + j + "] = " + table[i][j]);
        	}
        }
        return table[1][n];
    }

    // // sol 2: recursive.
    // public int getMoneyAmount(int n) {
    //     int[][] table = new int[n+1][n+1];
    //     return helper(table, 1, n);
    // }

    // private int helper(int[][] table, int start, int end) {
    // 	if (start >= end) {
    // 		return 0;
    // 	}
    // 	if (table[start][end] != 0) {	// already computed
    // 		return table[start][end];
    // 	}
    // 	int res = Integer.MAX_VALUE;
    // 	for (int i = start; i <= end; i++) {
    // 		int temp = i + Math.max(helper(table, start, i - 1), helper(table, i + 1, end));
    // 		res = Math.min(res, temp);
    // 	}
    // 	table[start][end] = res;
    // 	return res;
    // }
}