/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9]
 */

public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // idea: divide into 2 problems: 1. find the max number of length i within one array;
        // 2. merge 2 numbers from 2 arrays (i digits from nums1 and k-i digits from nums2)
        int m = nums1.length, n = nums2.length;
        if (k < 0 || m + n < k) {
        	return null;
        }
        int[] res = new int[k];
        if (k == 0) {
        	return res;
        }
        if (m + n == k) {
        	res = merge(nums1, nums2, k);
        	return res;
        } else {
        	// the max & min number of digits that should be selected from nums1
        	int max = m >= k ? k : m;
        	int min = n >= k ? 0 : k - n;
        	// Arrays.fill(res, -1);
        	for (int i = min; i <= max; i++) {
        		int[] temp = merge(getMax(nums1, i), getMax(nums2, k - i), k);
        		res = isGreater(res, 0, temp, 0) ? res : temp;
        	}
        	return res;
        }
    }

    // merge nums1 and nums2 to get a max number of length k
    private int[] merge(int[] nums1, int[] nums2, int k) {
    	int[] res = new int[k];
    	if (k == 0) {
    		return res;
    	}
    	int i = 0, j = 0;
    	for (int l = 0; l < k; l++) {
    		// choose the larger one to add to res
    		res[l] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
    	}
    	return res;
    }

    // compare nums1 from index i with nums2 from index j, return true if former is greater
    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
    	for (; i < nums1.length && j < nums2.length; i++, j++) {
    		if (nums1[i] > nums2[j]) {
    			return true;
    		} 
    		if (nums1[i] < nums2[j]) {
    			return false;
    		}
    	}
    	return i != nums1.length;	// if reaches the end
    }

    // get the max number of length k within single array nums
    private int[] getMax(int[] nums, int k) {
    	int[] res = new int[k];
    	if (k == 0) {
    		return res;
    	}
    	for (int i = 0, j = 0; j < nums.length; j++) {
    		// find a larger digit, then replace to the possible highest position to get larger
    		while (nums.length - j + i > k && i > 0 && res[i - 1] < nums[j]) {
    			i--;
    		}
    		if (i < k) {
    			res[i++] = nums[j];
    		}
    	}
    	return res;
    }
}