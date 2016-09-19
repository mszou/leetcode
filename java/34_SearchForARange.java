/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */

public class Solution {
    public int[] searchRange(int[] nums, int target) {
    	// idea: use Binary Search twice to find the left bound and right bound
        int[] bound = new int[2];
        bound[0] = bound[1] = -1;   //initialize
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return bound;
        }
        int start = 0, end = nums.length - 1, mid;
        // find the left bound
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    bound[0] = mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // find the right bound
        end = nums.length - 1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    bound[1] = mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return bound;
    }
}