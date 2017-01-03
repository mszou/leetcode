/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 * Example:
 * Input:
 * [1,2,3]
 * Output:
 * 3
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */

public class Solution {
	public int minMoves(int[] nums) {
		// idea: in the sense of making the elements equal, increasing n-1 elements by 1
		// is equivalent to decreasing 1 element by 1. so the minimal moves we need is to
		// make all elements in the array equal to the minimal element.		O(n) Time.
		if (nums == null || nums.length <= 1) {
			return 0;
		}
		int min = nums[0];
		// traverse to find the minimal element
		for (int n : nums) {
			min = Math.min(min, n);
		}
		int res = 0;
		// add up the moves needed to make each element equal to min
		for (int n : nums) {
			res += n - min;
		}
		return res;
	}
}