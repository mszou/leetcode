/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */

public class Solution {
	public int maximumGap(int[] nums) {
		// idea: bucket sort. the maximum gap >= ceiling[(max - min) / (N - 1)].
		// so let the bucket size < (max-min)/(N-1) then we only need to look for inter-bucket gaps.
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int max = nums[0], min = nums[0];
		for (int n : nums) {	// find the max & min value of the array
			max = Math.max(max, n);
			min = Math.min(min, n);
		}
		int interval = Math.max(1, (max - min) / nums.length);	// smaller than ceiling[(max-min) / (N-1)]
		int bucketNum = (max - min) / interval + 1;
		int[] bucketsMIN = new int[bucketNum];	// store min value in each bucket
		int[] bucketsMAX = new int[bucketNum];	// store max value in each bucket
		Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
		Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
		// put nums into bucket, update bucketsMIN & bucketsMAX
		for (int n : nums) {
			int idx = (n - min) / interval;	// compute which bucket it should go
			bucketsMIN[idx] = Math.min(bucketsMIN[idx], n);
			bucketsMAX[idx] = Math.max(bucketsMAX[idx], n);
		}
		int maxGap = Integer.MIN_VALUE;
		int previous = min;
		for (int i = 0; i < bucketNum; i++) {
			if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE) {
				continue;	// empty bucket
			}
			// the current gap is the min in current bucket minus the recorded previous value
			maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
			previous = bucketsMAX[i];
		}
		return maxGap;
	}
}