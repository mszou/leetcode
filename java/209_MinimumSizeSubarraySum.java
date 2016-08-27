/**
 * Given an array of n positive integers and a positive integer s, find the minimal 
 * length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // sol 1: two pointer, Time complexity: O(n)
        // for fixed start point, find the shortest eligible subarray and record the minLength so far
        int start = 0, end = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        for (start = 0; start < nums.length; start++) {
            // find the shortest eligible subarray starting from nums[start]
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }	// when out the while loop, end reaches the end or sum >= s and nums[end-1] is the end
            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
            }
            sum -= nums[start];
        }
        
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
        
        // sol 2: Binary search, Time complexity: O(nlogn)
        // finding min(j-i) s.t. sum[j] - sum[i] >= s is equivalent to given sum[i], find sum[j] >= sum[i] + s
    }
}