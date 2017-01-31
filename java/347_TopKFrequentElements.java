/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

public class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		// idea: bucket sort + HashMap<number, freq>. bucket[i] stores a list of numbers whose
		// frequency is i, add buckets from large to small until reach k.	O(n) Time
		List<Integer>[] bucket = new List[nums.length + 1];	// freq of any number <= nums.length
		HashMap<Integer> map = new HashMap<Integer, Integer>();
		for (int n : nums) {	// initialize map
			if (map.countainsKey(n)) {
				map.put(n, map.get(n) + 1);
			} else {
				map.put(n, 1);
			}
		}
		for (int key : map.keySet()) {	// initialize buckets
			int freq = map.get(key);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(key);
		}
		List<Integer> res = new ArrayList<>();
		for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
			if (bucket[i] != null) {
				res.addAll(bucket[i]);
			}
		}
		return res;
	}
}