/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class Solution {
    public int majorityElement(int[] nums) {
        // prerequisite: assume that the array is non-empty and the majority element always exist in the array.
        // idea: majority element appears more than ⌊n/2⌋ times, i.e. more than the total # of all other elements
        // so deleting a same number of major & minor elements won't affect majority. Thus we use a counter.
        // randomly pick a num as 'major', the counter increases when encounter the current 'major', and decreases
        // when encounter another number. if it's not the real major, the counter will become 0 sooner or later,
        // then turn to next "major"... after the traversal, the final major must be the real major
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int count = 0, major = -1;
        for (int n : nums) {
            if (count == 0) {   // no major now
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