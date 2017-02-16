/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */

public class Solution {
	// sol 1: use set. put all nums into set, then for each num, find the longest sequence containing
	// it by searching two directions for consecutive nums, and update res.	O(n) Time, O(n) Space.
	public int longestConsecutive(int[] nums) {
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

	// sol 2: use HashMap<num, len> to store each num and the length of longest consecutive
	// sequence that start or end with it. When we have a new num i (skip duplicates), look
	// for i-1 and i+1 in the map, store <i, left+right+1> into the map. use left and right
	// to locate the end(s) of the sequence and update value(s).	O(n) Time, O(n) Space.
	public int longestConsecutive(int[] nums) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			if (!map.containsKey(num)) {	// skip duplicates
				int left = map.getOrDefault(num - 1, 0);
				int right = map.getOrDefault(num + 1, 0);
				// the new sequence would from (num - left) to (num + right)
				int newLen = left + right + 1;
				map.put(num, newLen);
				if (left != 0) {	// update the left end
					map.put(num - left, newLen);
				}
				if (right != 0) {	// update the right end
					map.put(num + right, newLen);
				}
				res = Math.max(res, newLen);
			}
		}
		return res;
	}
}