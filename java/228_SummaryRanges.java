/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class Solution {
	public List<String> summaryRanges(int[] nums) {
		// idea: since the array is sorted and no duplicates, traverse the array and find the start and end
		// of every interval, add differently for single number or interval.	O(n) Time, O(1) Space.
		List<String> res = new ArrayList<String>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int n = nums.length;
		if (n == 1) {
			res.add("" + nums[0]);	// equivalent to String.valueOf(nums[0])
			return res;
		}
		for (int i = 0; i < n; i++) {
			int start = nums[i];
			while (i < n - 1 && nums[i + 1] == nums[i] + 1) {
				i++;
			}
			if (start == nums[i]) {	// single number
				res.add("" + start);
			} else {	// this is an interval
				res.add(start + "->" + nums[i]);
			}
		}
		return res;
	}
}