/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
 * Example 1:
 * nums = [1, 3], n = 6
 * Return 1.
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * Return 2.
 * The two patches can be [2, 4].
 * Example 3:
 * nums = [1, 2, 2], n = 5
 * Return 0.
 */

public class Solution {
	public int minPatches(int[] nums, int n) {
		// idea: Greedy. if x is the smallest number that the current sums cannot cover, 
		// (meaning [1,x-1] are all covered,) then adding x will make the sums cover [1,2x-1]
		int miss = 1;	// the first missing number is initialized as 1
		// change the type of miss to long if Time Limit Exceeded
		int patch = 0, i = 0;
		while (miss <= n) {
			if (i < nums.length && nums[i] <= miss) {
				miss += nums[i++];	// adding nums[i] can get sums in [1, miss+num[i]-1]
			} else {	// nums[i] > miss, cannot be reached, so patch is needed
				patch++;
				miss <<= 1;	// add a patch = miss, then the next missing number is 2 * miss
			}
		}
		return patch;
	}
}