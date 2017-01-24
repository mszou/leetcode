/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 */

public class Solution {
	public int minCost(int[][] costs) {
		// idea: DP.	O(n) Time, O(1) Space.
		// version 1: We can change the matrix to present accumulative sum of costs,
		// costs[i][j] is min cost for painting house 0~i with house i being painted
		// with color j. update costs[i][j] with the smaller one between costs[i-1][!j].
		if (costs == null || costs.length == 0) {
			return 0;
		}
		for (int i = 1; i < costs.length; i++) {
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
		}
		int n = costs.length - 1;
		return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);

		// version 2: use 3 variables to record the min cost for previous red, blue and green respectively.
		if (costs == null || costs.length == 0) {
			return 0;
		}
		int prevR = costs[0][0];
		int prevB = costs[0][1];
		int prevG = costs[0][2];
		for (int i = 1; i < costs.length; i++) {
			int currR = Math.min(lastB, lastG) + costs[i][0];
			int currB = Math.min(lastR, lastG) + costs[i][0];
			int currG = Math.min(lastR, lastB) + costs[i][0];
			prevR = currR;
			prevB = currB;
			prevG = currG;
		}
		return Math.min(Math.min(prevR, prevB), prevG);
	}
}