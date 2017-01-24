/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */

public class Solution {
    public int findMin(int[] nums) {
        // idea: No duplicates. Binary Search to find the position of pivot. O(logn) Time
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {    // left half in order, so min in the right half
                start = mid + 1;
            } else {    // right half in order, so min in the left half (including mid)
                end = mid;
            }
        }
        return nums[start];
    }
}