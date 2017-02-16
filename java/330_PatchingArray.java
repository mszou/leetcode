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
		// idea: Greedy. if x is the smallest number that the current sums cannot cover, then
		// adding x can make the sums cover [1,2x-1], which reaches farthest.	O(n) Time.
		int miss = 1;	// checking missing sum from 1, change type to long if TLE
		int patch = 0, i = 0;
		while (miss <= n) {	// make sure that sums that smaller than miss can always be formed
			if (i < nums.length && nums[i] <= miss) {	// nums[i] is reachable and can add it
				miss += nums[i++];	// adding nums[i] can get sums in [1, miss+num[i]-1]
			} else {	// nums[i] > miss, cannot be reached, so patch is needed
				patch++;
				miss <<= 1;	// add a patch = miss, then sums can cover 1 ~ 2 * miss - 1 now
			}
		}
		return patch;
	}
}