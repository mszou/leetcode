/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in O(n^2) runtime?
 */

public class Solution {
	// sol 1: naive, brute force, try every triplets and count sum < target, O(n^3) Time.
	
	// sol 2: sort, then for each first num, use two pointers to find eligible triplets.	O(n^2) Time
	public int threeSumSmaller(int[] nums, int target) {
		int count = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {	// traverse for first num, O(n)
			int left = i + 1, right = nums.length - 1;
			while (left < right) {	// two pointer, O(n)
				if (nums[i] + nums[left] + nums[right] < target) {
					count += right - left;	// the third num can be nums[left+1]~nums[right]
					left++;
				} else {
					right--;
				}
			}
		}
		return count;
	}
}