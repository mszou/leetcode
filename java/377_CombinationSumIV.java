/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */

public class Solution {
    public int combinationSum4(int[] nums, int target) {
    	// idea: DP. dp[i] represents # combinations to sum i. Then for each i from
        // 1 to traget, traverse nums, if j <= i, divide i as j + (i - j), and add
        // up those dp[i - j] to get dp[i].    O(n * target) Time, O(target) Space.
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
        	for (int num : nums) {
            	if (i - num >= 0) {
            		dp[i] += dp[i - num];
            	}
          	}
        }
        return dp[target];
    }
    // follow up: if has negative numbers, the combinations could be potentially of infinite length.
    // e.g. nums = [-1, 1] and target = 0, all combination with same number of 1's and -1's can work.
    // So we should limit the length of the combination sequence.
    // sol: we can write a recursive function with memorization to solve it.
}