/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 */

public class Solution {
    public int findMin(int[] nums) {
        // duplicates allowed. the worst case : [1,1,...,1,0,1,...,1]
        // so time complexity : O(n)
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {   // if mid == end, we don't know in which half that the min is located
                end--;
            } else if (nums[mid] < nums[end]) { // right half is in order, min lies in [start, mid]
                end = mid;
            } else {    // min lies in [mid + 1, end]
                start = mid + 1;
            }
        }
        return nums[start];
    }
}