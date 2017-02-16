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
	// idea: use deque (double-ended queue) to store indexes of potential max in the window range [i-k+1, i].
	// when the window slides, remove the numbers out of the window and the numbers < nums[i] from the deque.
	// As a result, nums in the deque are in non-ascending order, the head is always the max in current window.
	// O(n) Time, O(k) Space.
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return new int[0];
		}
		int n = nums.length;
		int[] res = new int[n - k + 1];
		int index = 0;
		Deque<Integer> q = new ArrayDeque<Integer>();	// store indexes, faster than LinkedList
		for (int i = 0; i < n; i++) {
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();	// remove the numbers out of the window range
			}
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {	// remove nums smaller than nums[i]
				q.pollLast();	// use peekLast & pollLast because nums in the deque are in non-ascending order
			}
			q.offer(i);
			if (i >= k - 1) {	// store the max in res starting from i = k - 1
				res[index++] = nums[q.peek()];
			}
		}
		return res;
	}
}