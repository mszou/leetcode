/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

public class Solution {
	public int[] singleNumber(int[] nums) {
		// idea: bit manipulation with XOR. O(n) Time, O(1) Space. Suppose x,y are the two single numbers,
		// two passes: first pass, XOR all elements, the result should exactly be x XOR y
		// x!=y, there must be at least a 1 in the result, means x and y are different in that bit
		// elements can be divided into two groups according whether they have 0 or 1 at that bit
		// then in the second pass, we do XOR to two groups separately to find x and y in either group
		int diff = 0;
		for (int n : nums) {
			diff ^= n;
		}
		
		diff &= -diff;	// get the rightmost 1 in diff
		int[] res = new int[2];	// res[0] and res[1] used for two groups respectively
		for (int num : nums) {
			if ((num & diff) == 0) {	// num has 0 on that bit
				res[0] ^= num;
			} else {
				res[1] ^= num;
			}
		}
		return res;
	}
}