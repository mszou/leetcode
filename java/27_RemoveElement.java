/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 * Hint:
 * Try two pointers.
 * Did you use the property of "the order of elements can be changed"?
 * What happens when the elements to remove are rare?
 */

public class Solution {
    public int removeElement(int[] nums, int val) {
    	// idea: use two pointers from the start and end, put elements of target value at the end by swapping
        int left = 0;
        int right = nums.length - 1;
        // elements behind pointer "right" are all instances of target value
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return right + 1;
    }
}