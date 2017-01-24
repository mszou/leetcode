/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class Solution {
	public int singleNumber(int[] nums) {
		// idea: bit manipulation. We need 2 bits to store (00, 01 and 10 as 0/3,1,2) loop: 00->01->10->00
		// 'ones' records the result of XOR of all numbers that have appeared once, 'twos' records the result
		// of XOR of all numbers that have appeared twice. First time a number appears, save it in 'ones'.
		// Second time, clear 'ones' and save it in 'twos'. Third time -> if 'twos' has it, then clear.
		// So the update rule is: ones = (ones ^ nums[i]) & ~twos; twos = (twos ^ nums[i]) & ~ones
		int ones = 0, twos = 0;
		for (int n : nums) {
			ones = (ones ^ n) & ~twos;
			twos = (twos ^ n) & ~ones;
		}
		return ones;
	}
}

// follow up: extending to "every element appears k times except for one"
// construct k - 1 intermediate values, the loop is: 0...0 -> 0..01 -> 0..10 ->..-> 10..0 -> 0...0
// if we use an array m[k-1] to store these intermediate value, the update rule for num becomes:
// for (int i = 0; i < k - 1; i++) {
// 	int temp = -1;
// 	for (int j = 0; j < k - 1; j++) {
// 		if (j != i) {
// 			temp &= ~m[j];
// 		}
// 	}
// 	m[i] = (m[i] ^ num) & temp;
// }
