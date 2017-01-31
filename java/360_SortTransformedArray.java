/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * Result: [3, 9, 15, 33]
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * Result: [-23, -5, 1, 7]
 */

public class Solution {
	// sol 1: straightforward, first compute the transformed array, then sort.	O(nlogn) Time.

	// sol 2: if a > 0, the parabola first decrease then increase; if a < 0, first up then down.
	// so use two pointers from two ends of the array, and do merge sort.	O(n) Time.
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if (nums == null || nums.length == 0) {
			return nums;
		}
		int n = nums.length;
		int[] res = new int[n];
		int i = 0, j = n - 1;
		int index = (a >= 0) ? n - 1 : 0;	// the starting index in sorted array (res)
		while (i <= j) {
			if (a >= 0) {
				res[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
			} else {
				res[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c) : quad(nums[i++], a, b, c);
			}
		}
		return res;
	}

	private int quad(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}
}