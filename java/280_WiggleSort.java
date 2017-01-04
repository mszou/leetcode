/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */

public class Solution {
	public void wiggleSort(int[] nums) {
		// idea: If i is odd, nums[i] >= nums[i - 1]; if i even, nums[i] <= nums[i - 1]
		// so we compare each pair when traversing, and swap them if needed
		if (nums == null || nums.length <= 1) {
			return;
		}
		for (int i = 1; i < nums.length; i++) {
			if (i % 2 == 1) {
				if (nums[i - 1] > nums[i]) {
					swap(nums, i - 1, i);
				}
			} else if (nums[i - 1] < nums[i]) {
				swap(nums, i - 1, i);
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}