/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class Solution {
	public int singleNumber(int[] nums) {
		// sol 1: bit manipulation (XOR). because a ^ a = 0 and XOR operator is commutative.
		int res = 0;
		for (int n : nums) {
			res ^= n;
		}
		return res;

		// sol 2: hash set, add a number when it first occurs and remove it upon second occurrance
		HashSet<Integer> set = new HashSet<Integer>();
		for (int n : nums) {
			if (set.contains(n)) {
				set.remove(n);
			} else {
				set.add(n);
			}
		}
		return set.iterator().next();	// use set.iterator().next() to get the number in the set
	}
}