/**
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 */

public class Solution {
    public int searchInsert(int[] nums, int target) {
        // idea: Binary Search
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {   // here is < instead of <=
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }   // when ends, left will be at the position to insert or the index of target
        return left;
    }
}