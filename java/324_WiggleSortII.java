/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

public class Solution {
	// sol 1: naive. sort then interleave the smaller half and larger half.	O(nlogn) Time, O(n) Space.
	// put median at the head (& tail) and interleave like: MLSLSM(362514) or MLSLS(35241). Otherwise
	// if interleave like SMSLML(142536), would be wrong for case (4,5,5,6), but (5,6,4,5) is correct
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
		Arrays.sort(nums);
		int[] res = new int[nums.length];
		// the index of (first) median is (nums.length - 1) / 2, left.size - right.size = 0 or 1
		int i = (nums.length - 1) / 2, j = nums.length - 1, idx = 0;
		while (j > (nums.length - 1) / 2) {
			res[idx++] = nums[i--];
			res[idx++] = nums[j--];
		}
		if (idx < nums.length) {	// for the case of odd length
			res[idx] = nums[i];
		}
		for (int k = 0; k < nums.length; k++) {
			nums[k] = res[k];	// assign res to nums
		}
	}

	// sol 2: For follow-up, O(n) Time, O(1) Space. use mapped Index (virtual index idea), e.g.
	// map origin indexes 012345 -> 135024, 01234 -> 13024, then the first half of mapped indexes
	// should be the position of numbers larger than median. assign larger numbers from left to right,
	// and assign smaller numbers from right to left using the mapped index of left & right pointers.
	// https://discuss.leetcode.com/topic/41464/step-by-step-explanation-of-index-mapping-in-java
	// the time complexity depends on findKthLargest, can be done in O(n) if we use quick select
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
		int n = nums.length;
		int median = findKthLargest(nums, (n + 1) / 2);	// find the (first) median
		int left = 0, i = 0, right = n - 1;
		while (i <= right) {
			if (nums[newIndex(i, n)] > median) {
				swap(nums, newIndex(left++, n), newIndex(i++, n));
			} else if (nums[newIndex(i, n)] < median) {
				swap(nums, newIndex(right--, n), newIndex(i, n));
			} else {
				i++;
			}
		}
	}

	// map index. e.g. 012345 -> 135024
	private int newIndex(int index, int n) {
		return (1 + 2 * index) % (n | 1);	// n | 1 = n (n is odd) or n + 1 (n is even)
	}

	// if use quick select: O(n) best / O(n^2) worst Time. O(1) Space.
	// if use heap of size k: O(nlogk) Time, O(k) Space
	private int findKthLargest(int[] nums, int k) {
		return quickSelect(nums, k - 1, 0, nums.length - 1);
	}

	private int quickSelect(int[] arr, int k, int left, int right) {
    	// choose a pivot element (mid), put all elements > pivot to the right, < pivot to the left
        int pivot = arr[left + (right - left) / 2];
        int orgL = left, orgR = right;
        while (left <= right) {
        	// from right to left, find the first element < pivot
            while (arr[left] > pivot) {
                left++;
            }
            // from left to right, find the first element > pivot
            while (arr[right] < pivot) {
                right--;
            }
            // swap arr[left] & arr[right]
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        // when while loop ends, it should be left == right + 1
        // if now right >= k, then k-th element should be in the left half
        if (orgL < right && k <= right) {
        	return quickSelect(arr, k, orgL, right);
        }
        // if now left <= k, then k-th element should be in the right half
        if (left < orgR && k >= left) {
        	return quickSelect(arr, k, left, orgR);
        }
        return arr[k];
    }
    
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}