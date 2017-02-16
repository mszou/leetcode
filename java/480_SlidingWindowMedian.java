/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * Note: 
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */

public class Solution {
	// sol 1: use two heaps, a maxHeap(smaller) for nums < median, a minHeap(larger) for nums >= median
	// always make larger.size() - smaller.size() == 0 or 1, add and remove nums while window sliding
	// add(num) is O(logk), remove(num) is O(k), getMedian() is O(1), so O(nk) Time, O(k) Space.
	PriorityQueue<Integer> larger = new PriorityQueue<Integer>();
	PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());

	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length - k + 1;	// total # medians
		if (n <= 0) {
			return new double[0];
		}
		double[] res = new double[n];
		for (int i = 0; i <= nums.length; i++) {	// window sliding
			if (i >= k) {
				res[i - k] = getMedian();
				remove(nums[i - k]);
			}
			if (i < nums.length) {
				add(nums[i]);
			}
		}
		return res;
	}

	private void add(int num) {
		if (num < getMedian()) {
			smaller.add(num);
		} else {
			larger.add(num);
		}
		if (smaller.size() > larger.size()) {
			larger.add(smaller.poll());
		}
		if (larger.size() - smaller.size() > 1) {
			smaller.add(larger.poll());
		}
	}

	private void remove(int num) {
		if (num < getMedian()) {
			smaller.remove(num);
		} else {
			larger.remove(num);
		}
		if (smaller.size() > larger.size()) {
			larger.add(smaller.poll());
		}
		if (larger.size() - smaller.size() > 1) {
			smaller.add(larger.poll());
		}
	}

	private double getMedian() {
		if (smaller.isEmpty() && larger.isEmpty()) {
			return 0;
		}
		if (smaller.size() == larger.size()) {
			return ((double)smaller.peek() + (double)larger.peek()) / 2.0;	// avoid interger overflow
		} else {
			return (double) larger.peek();
		}
	}


	// sol 2: TreeMap, can be O(nlogk) Time.
}