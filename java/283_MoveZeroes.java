/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

public class Solution {
    public void moveZeroes(int[] nums) {
        // idea: 2 pointers. move all the non-zero elements advance
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0, n = nums.length;
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        while (slow < n) {	// fill zeroes
            nums[slow++] = 0;
        }
    }
}