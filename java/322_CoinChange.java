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
	// sol 1: DP + recursive. count[i] is the min # coins for amount i
	public int coinChange(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}
		int[] count = new int[amount + 1];
		return helper(coins, amount, count);
	}

	private int helper(int[] coins, int remain, int[] count) {
		if (remain < 0) {
			return -1;
		}
		if (remain == 0) {
			return 0;
		}
		if (count[remain] != 0) {	// already considered all combinations for remain
			return count[remain];
		}
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = helper(coins, remain - coin, count);
			if (res != -1) {
				min = Math.min(min, res + 1);	// 1 is for this current coin
			}
		}
		count[remain] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[remain];
	}

	// sol 2: iterative. DP, count[i] is the min # coins for amount i. 	O(nm) Time, O(n) Space.
	public int coinChange(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}
		int[] count = new int[amount + 1];
		// if coins.length >> amount, no need to sort
		Arrays.sort(coins);	// O(mlogm) Time for sorting
		// iterate from sum = 1 to amount, update count[sum]
		for (int sum = 1; sum <= amount; sum++) {
			int min = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (sum < coin) {
					break;	// break from inner loop, cuz other coins after this are even larger
				}
				if (count[sum - coin] != -1) {
					min = Math.min(min, count[sum - coin] + 1);
				}
			}
			count[sum] = (min == Integer.MAX_VALUE) ? -1 : min;
		}
		return count[amount];
	}
}