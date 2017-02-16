/**
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * Example 1:
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 * Example 2:
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */

public class Solution {
	// idea: DP. dp[i][j] is the max # strings we can form with no more than i 0s and j 1s.
	// for each str, we update dp[] by checking if we can form more strs with choosing it.
	// O(kl + mn) Time, k = # strs, l = avg length of str. O(mn) Space.
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (String s : strs) {
			int[] count = count(s);	// count 0's and 1's in this string
			// choosing s needs at least count[0] 0's and count[1] 1's, so i>=count[0], j>=count[1]
			// iterate from larger to smaller to avoid this string being chosen more than once
			for (int i = m; i >= count[0]; i--) {
				for (int j = n; j >= count[1]; j--) {
					dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
				}
			}
		}
		return dp[m][n];
	}

	private int[] count(String str) {	// count 0's and 1's in this string
		int[] res = new int[2];	// res[0] for 0s, res[1] for 1s
		for (char c : str.toCharArray()) {
			res[c - '0']++;
		}
		return res;
	}
}