/**
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * Return the modified array after all k operations were executed.
 * Example:
 * Given:
 *     length = 5,
 *     updates = [
 *         [1,  3,  2],
 *         [2,  4,  3],
 *         [0,  2, -2]
 *     ]
 * Output:
 *     [-2, 0, 3, 5, 3]
 * Explanation:
 * Initial state:
 * [ 0, 0, 0, 0, 0 ]
 * After applying operation [1, 3, 2]:
 * [ 0, 2, 2, 2, 0 ]
 * After applying operation [2, 4, 3]:
 * [ 0, 2, 5, 5, 3 ]
 * After applying operation [0, 2, -2]:
 * [-2, 0, 3, 5, 3 ]
 * Hint:
 * Thinking of using advanced data structures? You are thinking it too complicated.
 * For each update operation, do you really need to update all elements between i and j?
 * Update only the first and end element is sufficient.
 * The optimal time complexity is O(k + n) and uses O(1) extra space.
 */

public class Solution {
	public int[] getModifiedArray(int length, int[][] updates) {
		// idea: utilize the accumulative sum. For each update, instead of increment all the numbers in the range,
		// we only add inc to the start number. Since we'll update res[i] as the accumulative sum before i, an
		// increased start will lead to increase in following numbers, so we also need to minus inc to the number
		// right after end to stop the impact of increasing to numbers out of the range.	O(k+n) Time, O(1) Space.
		if (length <= 0 || updates == null || updates.length == 0 || updates[0].length != 3) {
			return null;
		}
		int[] res = new int[length];	// all 0 by default
		for (int[] update : updates) {
			int start = update[0];
			int end = update[1];
			int value = update[2];
			res[start] += value;
			if (end < length - 1) {
				res[end + 1] -= value;
			}
		}
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += res[i];
			res[i] = sum;
		}
		return res;
	}
}