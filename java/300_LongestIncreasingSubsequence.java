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
        // // sol 1: DP. dp[i] is the length of LIS ending with nums[i]. For nums[i], traverse
        // // numbers before i and update dp[i] if find a longer IS.   O(n^2) Time, O(n) Space.
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

        // sol 2: DP + Binary Search. maxLen records the length of LIS so far while traversint the array. temp[i] records
        // the min end value of increasing sequence of length i+1 among all visited numbers so far. For each new number,
        // do binary search to find the insertIndex in temp. If insertIndex == maxLen, means the current number can be
        // appended to the previous maxLen IS and form an IS of length maxLen + 1, so increase maxLen.    O(nlogn) Time.
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