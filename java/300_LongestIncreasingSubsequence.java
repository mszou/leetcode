/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

public class Solution {
    public int lengthOfLIS(int[] nums) {
        // // sol 1: use DP. dp[i] is the length of LIS ending with nums[i]. O(n^2) Time.
        // int[] dp = new int[nums.length];
        // int maxLen = 0;
        // for (int i = 0; i < nums.length; i++) {
        // 	dp[i] = 1;
        // 	for (int j = 0; j < i; j++) {
        // 		if (nums[j] < nums[i]) {
        // 			dp[i] = Math.max(dp[i], dp[j] + 1);
        // 		}
        // 	}
        // 	maxLen = Math.max(maxLen, dp[i]);
        // }
        // return maxLen;

        // sol 2: use DP + Binary Search. O(nlogn) Time.
        int[] temp = new int[nums.length];
        int maxLen = 0;
        for (int x : nums) {
        	// use Arrays.binarySearch(int[] a, int fromIndex, int toIndex, int key)
        	// if key not found, returns (- insertion_index - 1)
            // here maxLen increase only when x > temp[maxLen], add one more number to the subsequence
        	int index = Arrays.binarySearch(temp, 0, maxLen, x);
        	if (index < 0) {   // not found, then get the insertion index
        		index = - (index + 1);
        	}
        	temp[index] = x;
        	if (index == maxLen) {
        		maxLen++;
        	}
        }
        return maxLen;
    }
}