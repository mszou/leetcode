/**
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 * Note: n will be less than 15,000.
 * Example 1:
 * Input: [1, 2, 3, 4]
 * Output: False
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * Input: [3, 1, 4, 2]
 * Output: True
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * Input: [-1, 3, 2, 0]
 * Output: True
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */

public class Solution {
	public boolean find132pattern(int[] nums) {
		// idea: use a stack, push numbers from right to left, and keep track of s2
		// each time have a new number, pop out all numbers smaller than it, and update s2
		Stack<Integer> stack = new Stack<Integer>();
		int s2 = Integer.MIN_VALUE;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] < s2) {
				return true;
			} else {
				while (!stack.empty() && nums[i] > stack.peek()) {
					s2 = Math.max(s2, stack.pop());
				}
			}
			stack.push(nums[i]);
		}
		return false;
	}
}