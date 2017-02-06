/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */

public class Solution {
    public boolean canPartition(int[] nums) {
        // idea: DP (backpack problem), equivalent to check if we can find a subset whose sum is half
        // of the total sum, because the rest nums will add up to the other half. dp[j] shows whether
        // subset sum == j can be composed. dp transition: dp[j] = dp[j] || dp[j-num[i]]     O(n) Time.
        if (nums == null || nums.length == 0) {
        	return false;
        }
        int sum = 0;
        for (int num : nums) {
        	sum += num;
        }
        if (sum % 2 != 0) {
        	return false;	// sum is odd
        }
        int target = sum / 2;	// the target subset sum
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];   // compose j without or with num
            }
            if (dp[target]) {
                return true;
            }
        }
        return false;
    }
}