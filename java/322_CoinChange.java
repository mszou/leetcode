/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */

public class solution {
	// // sol 1: DP + recursive
	// public int coinChange(int[] coins, int amount) {
	// 	if (amount < 1) {
	// 		return 0;
	// 	}
	// 	return helper(coins, amount, new int[amount + 1]);
	// }

	// // count[i] is the min # coins for amount i
	// private int helper(int[] coins, int remain, int[] count) {
	// 	if (remain < 0) {
	// 		return -1;
	// 	}
	// 	if (remain == 0) {
	// 		return 0;
	// 	}
	// 	if (count[remain] != 0) {	// already considered all combinations for remain
	// 		return count[remain];
	// 	}
	// 	int min = Integer.MAX_VALUE;
	// 	for (int coin : coins) {
	// 		int res = helper(coins, remain - coin, count);
	// 		if (res >= 0 && res < min) {	// find a fewer coin solution
	// 			min = res + 1;	// 1 is for this current coin
	// 		}
	// 	}
	// 	count[remain] = (min == Integer.MAX_VALUE) ? -1 : min;
	// 	return count[remain];
	// }

	// sol 2: iterative. DP, count[i] is the min # coins for amount i+1
	public int coinChange(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}
		int[] count = new int[amount + 1];
		int sum = 0;
		Arrays.sort(coins);
		// iterate from sum = 1 to amount
		while (++sum <= amount) {
			int min = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (sum < coin) {
					break;	// break from inner loop, cuz other coins after this are even larger
				}
				if (count[sum - coin] != -1) {
					int temp = count[sum - coin] + 1;
					min = Math.min(min, temp);
				}
			}
			count[sum] = (min == Integer.MAX_VALUE) ? -1 : min;
		}
		return count[amount];
	}
}