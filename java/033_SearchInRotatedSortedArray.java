/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */

public class Solution {
    public int search(int[] nums, int target) {
    	// idea: Binary Search, shrink the scope according to the comparisons
        int start = 0, end = nums.length - 1, mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] > nums[end]) {  // rotate point (pivot) at right, e.g. 3,4,5,6,1,2
                if (target > nums[mid] || target <= nums[end]) {    // right-hand side
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else {  // rotate point (pivot) at left, e.g. 5,6,1,2,3,4
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        if (start == end && target != nums[start]) {
            return -1;	// not found
        }
        return start;
    }
}