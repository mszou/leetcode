/**
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */

public class Solution {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		// idea: since the array is sorted, just traverse the array and keep tracking of the lower bound
		// (previous num + 1) of the current missing range, compare it with (next num - 1).	O(n) Time.
		List<String> res = new ArrayList<String>();
		for (int num : nums) {
			int justBelow = num - 1;
			if (lower == justBelow) {
				res.add(lower + "");	// convert lower to a string, i.e. String.valueOf(lower)
			} else if (lower < justBelow) {
				res.add(lower + "->" + justBelow);
			}
			lower = n + 1;
		}
		if (lower == upper) {
			res.add(lower + "");
		} else if (lower < upper) {
			res.add(lower + "->" + upper);
		}
		return res;
	}
}