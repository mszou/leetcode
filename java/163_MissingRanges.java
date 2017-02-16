/**
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */

public class Solution {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		// idea: since the array is sorted, just traverse the array and keep tracking of the lower bound
		// (previous + 1) of the current missing range, compare it with (next - 1).	O(n) Time, O(1) Space.
		List<String> res = new ArrayList<String>();
		for (int num : nums) {
			int justBelow = num - 1;
			if (lower == justBelow) {	// lower itself is the missing range (number)
				res.add(lower + "");	// convert lower to a string, i.e. String.valueOf(lower)
			} else if (lower < justBelow) {
				res.add(lower + "->" + justBelow);
			}	// else (lower > justBelow) means num is consecutive with previous num, so no missing between them
			lower = num + 1;
		}
		// remember to add the last range to res
		if (lower == upper) {
			res.add(lower + "");
		} else if (lower < upper) {
			res.add(lower + "->" + upper);
		}
		return res;
	}
}