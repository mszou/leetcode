/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */

public class Solution {
	public int findMaxConsecutiveOnes(int[] nums) {
		// idea: go through nums, keep tracking of current consecutive 1's length and update global max.	O(n) Time.
		int currLen = 0, max = 0;
		for (int num : nums) {
			if (num == 1) {
				currLen++;
				max = Math.max(max, currLen);
			} else {
				currLen = 0;
			}
		}
		return max;
	}
}