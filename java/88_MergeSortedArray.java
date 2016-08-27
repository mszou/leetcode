/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional 
 * elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	// idea: two pointers i & j pointing to the current greatest number in each array
    	// add integers from right to left, ensure that no data will be lost by overwriting
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[index--] = (nums1[i] >= nums2[j]) ? nums1[i--] : nums2[j--];
        }
        // if nums2 has integers left
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }
}