/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
 * It doesn't matter what you leave beyond the new length.
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
    	// idea: use two pointers, swap duplicate element with next unidentical number
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = 0;	// make sure no duplicates before pointer "size"
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[size]) {	// find next unidentical number: nums[i]
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }
}