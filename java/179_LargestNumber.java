/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class Solution {
	public String largestNumber(int[] nums) {
		// idea: convert the array into string array and sort to get the max number.	O(n) Space.
		// Time complexity: assume: nums.length=n, avg length of Strings in s_nums is k, then
		// Compare 2 strings:O(k); Sort:O(nlogn); Appending:O(n); Total:O(nklognk)+O(n)=O(nklognk)
		if (nums == null || nums.length == 0) {
			return "";
		}
		String[] s_nums = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			s_nums[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(s_nums, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String res1 = s1 + s2;
				String res2 = s2 + s1;
				return res2.compareTo(res1);
			}
		});
		if (s_nums[0].charAt(0) == '0') {	// corner case: all 0
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		for (String s : s_nums) {
			sb.append(s);
		}
		return sb.toString();
	}
}