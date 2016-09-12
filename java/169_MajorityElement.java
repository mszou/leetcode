/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class Solution {
	// majority element is the element that appears more than ⌊ n/2 ⌋ times.
    public int majorityElement(int[] nums) {
        // prerequisite: assume that the array is non-empty and the majority element always exist in the array.
        // idea: majority element appears more than ⌊ n/2 ⌋ times, i.e. more than the sum of all other elements
        // so deleting same number of major & minor elements won't affect majority. Thus use a counter.
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int count = 0, major = -1;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count = 1;
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }
}