/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

public class Solution {
	// idea: DP. group the nums following '+' as sum(P) and nums following '-' as sum(N), then
	// sum(P) - sum(N) = S, sum(P) + sum(N) = sum(nums). Thus this problem can be converted to
	// a subset problem: find a subset P, s.t. sum(P) = (S + sum) / 2, and assign '+' for them.
	// dp[i] is # subsets that can form sum i, so for each num, update dp[target] ~ dp[num].
	// O(nS) Time, O(S) Space.
	public int findTargetSumWays(int[] nums, int S) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (S > sum || (sum + S) % 2 == 1) {	// sum & S must be both odd or both even
			return 0;
		}
		return subsetSum(nums, (sum + S) / 2);
	}

	private int subsetSum(int[] nums, int target) {
		int[] dp = new int[target + 1];	// dp[i] is # ways to form sum i (only with '+')
		dp[0] = 1;
		for (int num : nums) {
			for (int j = target; j >= num; j--) {	// from target to num to avoid num being chosen multiple times
				dp[j] += dp[j - num];
			}
		}
		return dp[target];
	}
}