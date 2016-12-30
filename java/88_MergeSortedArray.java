/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional 
 * elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	// idea: merge from right to left, so that we do not need extra space and will not lose
        // data by overwriting. use 2 pointers pointing to the next numbers to be added in
        // each array and a third pointer pointing to the next position to be filled.
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[index--] = (nums1[i] >= nums2[j]) ? nums1[i--] : nums2[j--];
        }
        // no need to consider if nums1 has nums left, because they would be in the right place
        // if nums2 has integers left
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }
}