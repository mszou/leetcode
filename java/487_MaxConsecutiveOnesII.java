/** 
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */

public class Solution {
	public int findMaxConsecutiveOnes(int[] nums) {
		// idea: sliding window.	O(n) Time, O(1) Space.
		int max = 0, zero = 0, k = 1;	// for flip at most k zeroes
		int left = 0, right = 0;
		while (right < nums.length) {
			if (nums[right] == 0) {
				zero++;
			}
			while (zero > k) {
				if (nums[left++] == 0) {
					zero--;
				}
			}
			max = Math.max(max, right - left + 1);
			right++;
		}
		return max;
	}

	// follow-up: use a queue to store the indexes of zeroes within the window.
	// when size > k, poll one index out and move left to index+!.	O(n) Time, O(k) Space.
	public int findMaxConsecutiveOnes(int[] nums) {
		int max = 0, k = 1;	// for flip at most k zeroes
		Queue<Integer> zeroIndex = new LinkedList<>();
		int left = 0, right = 0;
		while (right < nums.length) {
			if (nums[right] == 0) {
				zeroIndex.offer(right);
			}
			if (zeroIndex.size() > k) {
				left = zeroIndex.poll() + 1;
			}
			max = Math.max(max, right - left + 1);
			right++;
		}
		return max;
	}
}