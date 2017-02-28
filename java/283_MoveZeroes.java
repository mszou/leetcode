/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

public class Solution {
    public void moveZeroes(int[] nums) {
        // idea: move all the non-zero nums to left, then fill the rest positions with 0. use two
        // pointers, one scans num, the other points to the pos in result.  O(n) Time, O(1) Space.
        if (nums == null || nums.length == 0) {
            return;
        }
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];  // move non-zero elements advance
            }
            fast++;
        }
        while (slow < nums.length) {	// fill zeroes to the rest positions
            nums[slow++] = 0;
        }
    }
}