/**
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 * Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 * Find the last number that remains starting with a list of length n.
 * Example:
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 * Output:
 * 6
 */

public class Solution {
	public int lastRemaining(int n) {
		// idea: we track the position of head and # remaining nums after each round. use a flag
		// to indicate whether start from left or right. When starting from left or remaining odd
		// nums, head will be eliminated, so move head to next num. After each round, # remaining
		// is halved and the step between adjacent nums doubled, until only 1 left.	O(logn) Time.
		boolean fromLeft = true;
		int remaining = n;
		int step = 1;
		int head = 1;
		while (remaining > 1) {
			if (fromLeft || remaining % 2 == 1) {
				head = head + step;
			}
			remaining /= 2;
			step *= 2;
			fromLeft =! fromLeft;
		}
		return head;
	}
}