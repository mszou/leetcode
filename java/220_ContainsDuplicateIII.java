/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 */

public class Solution {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
			return false;
		}
		// sol 1: TreeSet + sliding window. put numbers within the window of length k into a TreeSet.
		// for new num i, check if there is a num in the range [i-t, i+t] in the TreeSet.	O(nlogk) Time.
		TreeSet<Long> values = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			Long floor = values.floor((long)nums[i] + t);	// find the greatest element in the set <= (nums[i] + t)
			Long ceil = values.ceiling((long)nums[i] - t);	// find the smallest element in the set >= (nums[i] + t)
			if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) {
				return true;	// exist num in the range [nums[i] - t, nums + t]
			}
			values.add((long)nums[i]);
			if (i >= k) {
				values.remove((long)nums[i - k]);	// remove numbers out of the window
			}
		}
		return false;

		// sol 2: use buckets, create a mapping between values within a range of length t and a bucket.
		// for a num, if there is a num in the same bucket or adjacent bucket and distance <= t, then false.
		// always keep at most k existing bucket, when exceed, remove the oldest one.	O(n) Time.
		Map<Long, Long> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			long remappedNum = (long) nums[i] - Integer.MIN_VALUE;	// need to map the whole range of integer
			long bucket = remappedNum / ((long) t + 1);	// mapping this number to a bucket
			if (map.containsKey(bucket) || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t) || map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t) {
				return true;
			}
			if (map.entrySet().size() >= k) {
				long oldestBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
				map.remove(oldestBucket);
			}
			map.put(bucket, remappedNum);
		}
		return false;
	}
}