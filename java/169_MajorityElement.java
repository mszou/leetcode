/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class Solution {
    public int majorityElement(int[] nums) {
        // prerequisite: assume that the array is non-empty and the majority element always exist in the array.
        // idea: majority element appears more than ⌊n/2⌋ times, i.e. more than the sum # of all other elements
        // so deleting same number of major & minor elements won't affect majority. Thus use a counter.
        // randomly take a num as major, if it's not the real major, the counter will become 0 sooner or later
        // then turn to next "major"... after the traversal, major must be the real major
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int count = 0, major = -1;
        for (int n : nums) {
            if (count == 0) {
                major = n;
                count++;
            } else if (major == n) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }
}