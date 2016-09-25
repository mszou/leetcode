/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */

public class Solution {
    public int findMin(int[] nums) {
        // No duplicates. Binary Search to find the position of pivot. O(logn) Time
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {    // min at the right half
                start = mid + 1;
            } else {    // min at the left half
                end = mid;
            }
        }
        return nums[start];
    }
}