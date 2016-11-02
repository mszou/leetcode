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
        // idea: DP. like backpack problem. if we can find a subset whose sum is half of the total sum, 
        // then the rest elements can add up to the same sum. dp[j] is whether subset sum == j can be composed
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
        sum /= 2;	// the subset sum
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
        	for (int j = sum; j >= nums[i]; j--) {
        		dp[j] = dp[j] || dp[j - nums[i]];	// compose j without/with nums[i]
        		// System.out.println("dp[" + j + "] = " + dp[j]);
        	}
        	if (dp[sum]) {
        		return true;
        	}
        }
        return false;
    }
}