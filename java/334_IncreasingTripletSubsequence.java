/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k 
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * Given [5, 4, 3, 2, 1],
 * return false.
 */

public class Solution {
	public boolean increasingTriplet(int[] nums) {
		// idea: use two variables to store the candidates of first two numbers in the triplet
		// Traverse the array and update small & large, we make sure that 'small' is the min
		// num so far and 'large' always has a smaller num before it.	O(n) Time, O(1) Space.
		int small = Integer.MAX_VALUE, large = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small) {
				small = n;
			} else if (n <= large) {
				large = n;
			} else {
				return true;
			}
		}
		return false;
	}
}