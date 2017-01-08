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
        // O(logn) Time, O(1) Space.    Note the different update rules in Binary Searches
        int[] bound = new int[2];
        bound[0] = bound[1] = -1;   // initialize as [-1, -1]
        if (nums == null || nums.length == 0) {
            return bound;
        }
        if (target < nums[0] || target > nums[nums.length - 1]) {   // target out of the range
            return bound;
        }
        int start = 0, end = nums.length - 1, mid;
        
        // Version 1:
        // first find the left bound
        while (start < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }   // end with start == end == index or insertion position of left bound
        if (nums[start] != target) {    // target not exists
            return bound;
        } else {
            bound[0] = start;
        }
        // then find the right bound
        end = nums.length - 1;
        while (start < end) {
            mid = start + (end - start) / 2 + 1;    // make mid biased to the right
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }   // end with start == end == index of right bound
        bound[1] = end;
        return bound;
        
        // // Version 2:
        // // first find the left bound
        // while (start <= end) {
        //     mid = start + (end - start) / 2;
        //     if (nums[mid] >= target) {
        //         if (nums[mid] == target) {
        //             bound[0] = mid;
        //         }
        //         end = mid - 1;
        //     } else {
        //         start = mid + 1;
        //     }
        // }   // end with start == end + 1 == bound[0]
        // // then find the right bound
        // end = nums.length - 1;
        // while (start <= end) {  // now start = bound[0]
        //     mid = start + (end - start) / 2;
        //     if (nums[mid] <= target) {
        //         if (nums[mid] == target) {
        //             bound[1] = mid;
        //         }
        //         start = mid + 1;
        //     } else {
        //         end = mid - 1;
        //     }
        // }   // end with end == start - 1 == bound[1]
        // return bound;
    }
}