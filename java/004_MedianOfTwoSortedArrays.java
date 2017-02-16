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

	// idea: Binary Search + Divide & Conquer, recursively compare medians of two remaining arrays and shrink
	// the scope of global median by half. if aMid < bMid, keep aRight & bLeft; otherwise, keep aLeft & bRight

	// Version 1: write a function findKth to find the k-th element in two sorted arrays.	O(log(m+n)) Time.
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, length / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, length / 2) + findKth(nums1, 0, nums2, 0, length / 2 + 1)) / 2.0;
        }
    }
    
    // find the k-th element in sorted nums1[start1,...,end1] & nums2[start2,...,end2], each time shrink k/2
    private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
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
		    return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
		} else {
		    return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
		}
    }

    // Version 2: median divides a set into two halves and one half is always no less than the other.
    // so we try to find the way to divide. Suppose we take i nums from nums1 and j nums from nums2 as
    // left part, then i+j should be (m+n+1)/2. Binary search for i to get a good division. Then the
    // median should be leftMax (odd) or avg of leftMax & rightMin (even).	O(log(min(m,n))) Time.
    //       left_part          |        right_part
	// A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
	// B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int m = nums1.length;
    	int n = nums2.length;
    	if (m > n) {	// suppose nums1 is shorter than nums2, otherwise, swap
    		return findMedianSortedArrays(nums2, nums1);
    	}
    	int imin = 0, imax = m;	// range for i
    	int i = (imin + imax) / 2, j= (m + n + 1) / 2 - i;	// make sure i+j always = half length (as left part)
    	while (imin <= imax) {  // binary search for i
    		i = (imin + imax) / 2;
    		j = (m + n + 1) / 2 - i;
    		if (i > 0 && nums1[i - 1] > nums2[j]) {	// means i is too large
    			imax = i - 1;
    		} else if (i < m && nums2[j - 1] > nums1[i]) {	// means i is too small
    			imin = i + 1;
    		} else {	// i is perfect
    			break;
    		}
    	}
    	int max_of_left, min_of_right;
    	if (i == 0) {	// no nums1 in left part
    		max_of_left = nums2[j - 1];
    	} else if (j == 0) {	// no nums2 in left part
    		max_of_left = nums1[i - 1];
    	} else {
    		max_of_left = Math.max(nums1[i - 1], nums2[j - 1]);
    	}
    	if ((m + n) % 2 == 1) {	// total length is odd, so median is max_of_left
    		return max_of_left;
    	}
    	if (i == m) {	// no nums1 in right part
    		min_of_right = nums2[j];
    	} else if (j == n) {
    		min_of_right = nums1[i];
    	} else {
    		min_of_right = Math.min(nums1[i], nums2[j]);
    	}
    	return (max_of_left + min_of_right) / 2.0;
    }
}