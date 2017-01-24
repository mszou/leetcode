/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 * Hint:
 * How many majority elements could it possibly have?
 */

public class Solution {
	public List<Integer> majorityElement(int[] nums) {
		// idea: it can have at most 2 majority elements that appear more than ⌊ n/3 ⌋ times. randomly choose 2 candidates
		// and count frequecy, check whether the candidates we finally got are majority elements.	O(n) Time, O(1) Space.
		List<Integer> res = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int candidate1 = 0, candidate2 = 0;
		int count1 = 0, count2 = 0;
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (candidate1 == nums[i]) {
				count1++;
			} else if (candidate2 == nums[i]) {
				count2++;
			} else if (count1 == 0) {
				candidate1 = nums[i];	// change for a new candidate
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = nums[i];
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = count2 = 0;	// reset to count the frequency of candidates we finally got.
		for (int i = 0; i < n; i++) {
			if (nums[i] == candidate1) {
				count1++;
			} else if (nums[i] == candidate2) {
				count2++;
			}
		}
		if (count1 > n / 3) {
			res.add(candidate1);
		}
		if (count2 > n / 3) {
			res.add(candidate2);
		}
		return res;
	}
}