/**
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 */

public class Solution {
	public boolean containsDuplicate(int[] nums) {
		// idea: HashSet, O(n) Time, O(n) Space
		// if the array is sorted, just compare adjacent pairs, O(n) Time, O(1) Space
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			if (!set.add()) {
				return true;
			}
		}
		return false;
	}
}