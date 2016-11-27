/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * Note: 
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * Follow up:
 * Could you solve it in linear time?
 * Hint:
 * 1. How about using a data structure such as deque (double-ended queue)?
 * 2. The queue size need not be the same as the window’s size.
 * 3. Remove redundant elements and the queue should store only elements that need to be considered.
 */

public class Solution {
	// idea: use a deque (double-ended queue) to store indices of promising elements in the window range [i-k+1, i].
	// when moving the window, disgard elements before i-k+1 and elements smaller than nums[i]
	// As a result elements in the deque are in non-ascending order, so the head is always the max element in current window.
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return new int[0];
		}
		int len = nums.length;
		int[] res = new int[len - k + 1];
		int rIdx = 0;
		Deque<Integer> q = new ArrayDeque<Integer>();	// store indices
		for (int i = 0; i < nums.length; i++) {
			// remove numbers out of the range
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller elements
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();	// use peekLast & pollLast because elements in the deque are in non-ascending order
			}
			q.offer(i);
			if (i >= k - 1) {	// store the max in res from i = k - 1
				res[rIdx++] = nums[q.peek()];
			}
		}
		return res;
	}
}