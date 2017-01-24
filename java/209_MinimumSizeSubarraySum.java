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
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // sol 1: two pointer, compute the sum of nums within the range of two pointers. For a start point, move end
        // pointer to find the shortest eligible subarray, update minLen, then move start pointer.  O(n) Time, O(1) Space.
        int start = 0, end = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        for (start = 0; start < nums.length; start++) {
            // find the shortest eligible subarray starting from nums[start]
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }   // when out the while loop, end reaches the end or sum >= s, nums[end-1] is the last one counted in sum
            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
            }
            sum -= nums[start]; // move the start pointer
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
        
        
        // // sol 2: Binary search, compute accumulative sums of the array, then the problem is equivalent to finding
        // // min(j-i) s.t. sum[j]-sum[i] >= s, or given sum[i], find sum[j] >= sum[i] + s.   O(nlogn) Time, O(n) Space.
        // int[] accuSum = new int[nums.length + 1];
        // accuSum[0] = 0;
        // for (int i = 1; i <= nums.length; i++) {
        //     accuSum[i] = accuSum[i - 1] + nums[i - 1];
        // }
        // int start = 0, end, mid;
        // int minLen = Integer.MAX_VALUE;
        // for (int i = 0; i <= nums.length; i++) {
        //     end = nums.length;
        //     while (start < end) {
        //         mid = (start + end) / 2;
        //         if (accuSum[mid] >= accuSum[i] + s) {
        //             end = mid;
        //         } else {
        //             start = mid + 1;
        //         }
        //     }
        //     if (accuSum[end] >= accuSum[i] + s) {
        //         minLen = Math.min(minLen, end - i);
        //     }
        //     start = i + 1;
        // }
        // return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
}