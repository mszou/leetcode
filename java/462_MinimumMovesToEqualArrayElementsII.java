/**
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * You may assume the array's length is at most 10,000.
 * Example:
 * Input:
 * [1,2,3]
 * Output:
 * 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */

public class Solution {
	public int minMoves2(int[] nums) {
		// idea: like meeting point problem, should choose the median (for odd length nums) or any number
		// between 2 medians (for even length nums). Use sorting or quick select to partition the array.
		// O(nlogn) Time if use sorting, best O(n) worst O(n^2) Time if use quick select; O(1) Space.
		Arrays.sort(nums);
		int i = 0, j  = nums.length - 1;
		int count = 0;
		while (i < j) {
			count += nums[j] - nums[i];	// sum of moves needed for nums[i] & nums[j]
			i++;
			j--;
		}
		return count;
	}
}