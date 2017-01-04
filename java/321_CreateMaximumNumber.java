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
        // idea: divide into 2 sub-problems: 1. find the max number of length i within one array;
        // 2. merge 2 numbers from 2 arrays (i digits from nums1 and k-i digits from nums2)
        int m = nums1.length, n = nums2.length;
        if (k < 0 || k > m + n) {
        	return null;
        }
        int[] res = new int[k];
        if (k == 0) {
        	return res;
        }
        if (k == m + n) {
        	res = merge(nums1, nums2);
        	return res;
        }
        // determine the max & min number of digits that should be picked from nums1
        int max = m >= k ? k : m;
        int min = n >= k ? 0 : k - n;
        for (int i = min; i <= max; i++) {
            int[] temp = merge(getMax(nums1, i), getMax(nums2, k - i));
            res = isGreater(res, 0, temp, 0) ? res : temp;
        }
        return res;
    }

    // merge two arrays nums1 and nums2 to get a max result
    private int[] merge(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
    	int[] res = new int[len];
    	int i = 0, j = 0;  // two pointers
    	for (int l = 0; l < len; l++) {
    		// choose the larger one to add to res, here use isGreater instead of nums1[i] > nums2[j] to
            // deal with the "same" situation, e.g. 3,5 & 3,6 should add the 3 in the second array first
    		res[l] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
    	}
    	return res;
    }

    // compare nums1 from index i with nums2 from index j, digit by digit
    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
    	for (; i < nums1.length && j < nums2.length; i++, j++) {
    		if (nums1[i] > nums2[j]) {
    			return true;
    		} 
    		if (nums1[i] < nums2[j]) {
    			return false;
    		}
    	}
    	return i != nums1.length;	// if nums1 has more elements
    }

    // get the max number array of length k within single array nums
    private int[] getMax(int[] nums, int k) {
    	int[] res = new int[k];
    	if (k == 0) {
    		return res;
    	}
        // i is the pointer for the result array, j is the pointer for nums array
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