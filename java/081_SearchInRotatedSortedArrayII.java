/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 */

public class Solution {
    public boolean search(int[] nums, int target) {
        // worst case: [1,1,...,1,0,1,...1]
        // For the worst case, the complexity for Binary Search is the same as a for loop as follows (O(n))
        
        // for loop
        /*for (int i = 0; i < A.length; i ++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;*/
        
        // Binary Search
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;    // return mid in Search in Rotated Array I
            }
            if (nums[left] < nums[mid]) { // left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) { // right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {    // cannot tell which part sorted
                left ++;
            }
        }
        return false;
    }
}