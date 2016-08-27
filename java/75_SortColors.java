/**
 * Given an array with n objects colored red, white or blue, sort them so that objects 
 * of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 */

public class Solution {
    public void sortColors(int[] nums) {
    	// idea: use two pointers from left and right
    	// put 0's before "left" and 2's after "right" by swapping
        if (nums == null || nums.length <= 1) {
            return;
        }
        int left = 0, right = nums.length - 1;	// set two pointers
        for (int i = 0; i <= right; ) {	// i is the current pointer, analyze nums[i]
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
        return;
    }
    
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}