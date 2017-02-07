/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2. 
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
 * Hence, player 1 will never be the winner and you need to return False.
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 */

public class Solution {
	// sol 1: recursive, check whether the max difference this player can get is positive
	public boolean PredictTheWinner(int[] nums) {
		return helper(nums, 0, nums.length - 1, new Integer[nums.length][nums.length]) >= 0;
	}

	// returns the max difference this player can get when facing nums[start, end]
	private int helper(int[] nums, int start, int end, Integer[][] mem) {
		if (mem[start][end] == null) {	// never computed before
			if (start == end) {
				mem[start][end] = nums[start];
			} else {	// 
				mem[start][end] = Math.max(nums[end] - helper(nums, start, end - 1, mem), nums[start] - helper(nums, start + 1, end, mem));
			}
		}
		return mem[start][end];
	}


	// sol 2: interval DP. our opponent also play optimally, so we can only get the smaller res after his choice.
	// dp[i][j] = max{ min{dp[i+1][j-1],dp[i+2][j]} + nums[i], min{dp[i][j-2],dp[i+1][j-1]} + nums[j]}	O(n^2) Time.
	public boolean PredictTheWinner(int[] nums) {
		if (nums.length % 2 == 0) {
			return true;
		}
		int n = nums.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int myBest = helper(nums, dp, 0, n - 1);
		return myBest >= sum - myBest;
	}

	private int helper(int[] nums, int[][] dp, int i, int j) {
		if (i > j) {
			return 0;
		}
		if (dp[i][j] != -1) {	// already computed
			return dp[i][j];
		}
		int chooseI = nums[i] + Math.min(helper(nums, dp, i+1, j-1), helper(nums, dp, i+2, j));
		int chooseJ = nums[j] + Math.min(helper(nums, dp, i, j-2), helper(nums, dp, i+1, j-1));
		dp[i][j] = Math.max(chooseI, chooseJ);
		return dp[i][j];
	}
}