/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 * Example:
 * Input: [3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */

public class Solution {
	public int findMaximumXOR(int[] nums) {
		// idea: begin from the most significant bit (MSB), with mask 100..0, 110..0,..., 1..10, 1..11,
		// iterately determine each bit of the final result. use a hashset to store the first i bits of
		// each number (num & mask), and update the max by checking the existance of greater XOR result.
		// an important law: if a ^ b = c, then c ^ a = b, c ^ b = a
		// e.g. given [14, 11, 7, 3]([1110, 1011, 0111, 0011]), then starting from MSB i = 3
		// 1. i = 3, mask = 1000, set = {1000, 0000}, max = 1000;
		// 2. i = 2, mask = 1100, set = {1100, 1000, 0100, 0000}, max = 1100;
		// 3. i = 1, mask = 1110, set = {1110, 1010, 0110, 0010}, max = 1100;
		// 4. i = 0, mask = 1111, set = {1110, 1011, 0111, 0011}, max = 1101;
		int max = 0, mask = 0;
		for (int i = 31; i >= 0; i--) {
			mask |= (1 << i);
			HashSet<Integer> prefixSet = new HashSet<Integer>();
			for (int num : nums) {
				prefixSet.add(num & mask);
			}
			int temp = max | (1 << i);	// an greater XOR result that we might get in this iteration
			for (int prefix : prefixSet) {
				if (prefixSet.contains(temp ^ prefix)) {
					max = temp;
					break;
				}
			}
		}
		return max;
	}
}