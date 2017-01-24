/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Note:
 * Your solution should be in logarithmic complexity.
 */

public class Solution {
    public int findPeakElement(int[] nums) {
        // idea: Binary Search. Because nums[i] ≠ nums[i+1], we can compare nums[mid] & nums[mid+1] and
        // move pointers to let nums[start] > nums[start-1] and nums[end] > nums[end-1]. O(logn) Time.
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + （end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;    // move start
            } else {
                end = mid;  // move end
            }
        }
        return start;
    }
}