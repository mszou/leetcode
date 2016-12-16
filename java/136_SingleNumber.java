/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class Solution {
	public int singleNumber(int[] nums) {
		// sol 1: bit manipulation (XOR). a XOR a = 0, and XOR operator is commutative.
		int res = 0;
		for (int n : nums) {
			res ^= n;
		}
		return res;

		// sol 2: hash set
		HashSet<Integer> set = new HashSet<Integer>();
		for (int n : nums) {
			if (set.contains(n)) {
				set.remove(n);
			} else {
				set.add(n);
			}
		}
		return set.iterator().next();
	}
}