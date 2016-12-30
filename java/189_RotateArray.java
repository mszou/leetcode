/**
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * Related problem: Reverse Words in a String II
 */

public class Solution {
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int n = nums.length;

		// sol 1: Make an extra copy and then rotate. O(n) Time, O(n) Space.
		int[] copy = new int[n];
		System.arraycopy(nums, 0, copy, 0, nums.length);
		for (int i = 0; i < nums.length; i++) {
			nums[(i + k) % n] = copy[i];
		}

		// sol 2: Start from one number replace the num in its target position,
		// keep rotating until finish all n numbers. O(n) Time, O(1) Space.
		int count = 0, start = 0, idx = 0, temp = 0;
		int numToRotate = nums[0];	// start from the first num
		while (count < n) {
			do {
				idx = (idx + k) % n;
				temp = nums[idx];
				nums[idx] = numToRotate;
				numToRotate = temp;	// next num to be rotated
				count++;
			} while (idx != start);
			// idx reaches start doesn't necessarily mean finish all numbers
			idx = ++start;
			numToRotate = nums[idx];
		}

		// sol 3: Reverse the first (n - k) elements, the last k elements, and then
		// reverse all n elements. O(n) Time, O(1) Space.
		k = k % n;
		reverse(nums, 0, n - k - 1);	// reverse the first (n - k) elements
		reverse(nums, n - k, n - 1);	// reverse the last k elements
		reverse(nums, 0, n - 1);		// reverse all n elements
	}

	// reverse the array nums from index 'start' to index 'end'
	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
}