/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

public class Solution {
    public void moveZeroes(int[] nums) {
        // idea: move all the non-zero elements advance, then fill the rest places with 0.
        // use two pointers, one for scanning nums, the other points to the pos in result.
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];  // move non-zero elements advance
            }
        }
        while (slow < nums.length) {	// fill zeroes
            nums[slow++] = 0;
        }
    }
}