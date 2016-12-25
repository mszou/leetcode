/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */

public class Solution {
	public int longestConsecutive(int[] nums) {
		// idea: put all nums into a set, for each number, find the longest sequence containing it
		// by checking the existance of consecutively greater & smaller numbers. update the result
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		int longest = 0;
		for (int n : nums) {
			if (!set.contains(n)) {	// already visited
				continue;
			}
			int down = n - 1;
			while (set.contains(down)) {
				set.remove(down);
				down--;
			}
			int up = n + 1;
			while (set.contains(up)) {
				set.remove(up);
				up++;
			}
			longest = Math.max(longest, up - down - 1);
		}
		return longest;
	}
}