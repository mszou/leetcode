/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

public class Solution {
	// idea: from right to left, find the first number A that goes smaller (after which is
	// a non-ascending suffix), then from right(tail) to left find the first number B that
	// is greater than A, swap A & B and reverse the numbers after B.	O(n) Time, O(1) Space.
	// e.g. 1 2 7 4 3 1, first find 2 as A (2 < 7), then find 3 as B (3 > 2), swap 2 & 3,
	// get: 1 3 7 4 2 1, then reverse numbers after 3 --> 1 3 1 2 4 7 is the next permutation
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int len = nums.length;
		for (int i = len - 2; i >= 0; i--) {
			if (nums[i + 1] > nums[i]) {	// nums[i + 1] is the rightmost peak
				int j = len - 1;
				while (nums[j] <= nums[i]) {
					j--;	// find the rightmost number that is greater than nums[i]
				}
				swap(nums, i, j);	// swap nums[i] & nums[j]
				reverse(nums, i + 1, len - 1);	// reverse the part after index i
				return;
			}
		}
		// if didn't return in the for loop, nums must be the last permutation (descending)
		reverse(nums, 0, len - 1);	// reverse the whole array to get the first permutation
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private void reverse(int[] nums, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			swap(nums, i, j);
		}
	}
}