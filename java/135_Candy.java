/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */

public class Solution {
	public int candy(int[] ratings) {
		// idea: first give each child one candy, then traverse from left to right, if ratings[i] > ratings[i-1]
		// give more candy to i s.t. i has 1 more than i-1; traverse from right to left and do the same thing
		if (ratings == null || ratings.length == 0) {
			return 0;
		}
		int[] candies = new int[ratings.length];
		Arrays.fill(candies, 1);
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				if (candies[i] < candies[i + 1] + 1) {
					candies[i] = candies[i + 1] + 1;
				}
			}
		}
		int sum = 0;
		for (int candy : candies) {
			sum += candy;
		}
		return sum;
	}
}