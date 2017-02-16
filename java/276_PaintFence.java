/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 * Note: n and k are non-negative integers.
 */

public class Solution {
	public int numWays(int n, int k) {
		// idea: DP, consider 2 cases: last two posts have the same color or have different colors.
		// if last two same, curr must be different; otherwise, curr & prev can be same or different
		// in other words, curr same with prev only when last two diff and curr has only 1 choice;
		// while curr diff from prev, then curr has k-1 choices and no matter last two diff or same.
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}
		int lastTwoSame = k;
		int lastTwoDiff = k * (k - 1);
		for (int i = 2; i < n; i++) {
			int temp = lastTwoDiff;
			lastTwoDiff = (lastTwoDiff + lastTwoSame) * (k - 1);
			lastTwoSame = temp;
		}
		return lastTwoSame + lastTwoDiff;
	}
}