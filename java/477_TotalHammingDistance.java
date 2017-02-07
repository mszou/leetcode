/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 * Example:
 * Input: 4, 14, 2
 * Output: 6
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * Note:
 * Elements of the given array are in the range of 0 to 10^9
 * Length of the array will not exceed 10^4.
 */

public class Solution {
	public int totalHammingDistance(int[] nums) {
		// idea: for 1st pos to 32th pos, count # integers in the array that have 1 at that bit.
		// If k out of n nums have that bit set, then that bit contributes k(n-k) hamming distance.
		// We can get the total hamming distance by adding those up.	O(n) Time, O(1) Space.
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int n = nums.length;
		int total = 0;
		for (int j = 0; j < 32; j++) {
			int count1 = 0;	// count # integers that have 1 at pos j
			for (int i = 0; i < n; i++) {
				count1 += (nums[i] >> j) & 1;
			}
			total += count1 * (n - count1);	// multiplication principle
		}
		return total;
	}
}