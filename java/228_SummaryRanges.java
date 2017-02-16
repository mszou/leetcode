/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class Solution {
	public List<String> summaryRanges(int[] nums) {
		// idea: since the array is sorted and no duplicate, traverse the array and find the start & end
		// of every interval. Add differently for single number and interval.	O(n) Time, O(1) Space.
		List<String> res = new ArrayList<String>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int n = nums.length;
		if (n == 1) {	// actually no need to treat n == 1 differently
			res.add("" + nums[0]);	// equivalent to String.valueOf(nums[0])
			return res;
		}
		for (int i = 0; i < n; i++) {
			int start = nums[i];
			while (i < n - 1 && nums[i + 1] == nums[i] + 1) {
				i++;	// find the end of continuous sequence
			}
			if (start == nums[i]) {	// this is a single number
				res.add("" + start);
			} else {	// this is an interval
				res.add(start + "->" + nums[i]);
			}
		}
		return res;
	}
}