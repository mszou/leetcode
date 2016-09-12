/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */


public class Solution {
	/**
	 * @param nums1 A sorted array of size m
	 * @param nums2 Another sorted array of size n
	 * @return median (double) of the two arrays
	 */

	// idea: Divide and Conquer, compare medians of the two arrays then shrink the scope of global median
	// use a self-defined function find_kth to find the k-th element in two sorted arrays

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 1) {
            return find_kth(nums1, 0, nums2, 0, length / 2 + 1);
        } else {
            return (find_kth(nums1, 0, nums2, 0, length / 2) + find_kth(nums1, 0, nums2, 0, length / 2 + 1)) / 2.0;
        }
    }
    
    // find the k-th element in nums1[start1,...,end] + nums2[start2,...,end]
    private static int find_kth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) {
			return nums2[start2 + k - 1];
		}
		if (start2 >= nums2.length) {
			return nums1[start1 + k - 1];
		}
		if (k == 1) {
			return Math.min(nums1[start1], nums2[start2]);
		}
		int key1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
		int key2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
		if (key1 < key2) {
		    return find_kth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
		} else {
		    return find_kth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
		}
    }
}