/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note: 
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Examples: 
 * input: 1
 * output: 
 * []
 * input: 37
 * output: 
 * []
 * input: 12
 * output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 * input: 32
 * output:
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 */

public class Solution {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (n <= 3) {
			return res;	// no factors for n <= 3
		}
		getFactors(res, new ArrayList<Integer>(), n, 2);
		return res;
	}

	private void getFactors(List<List<Integer>> res, List<Integr> curr, int n, int start) {
		if (n == 1) {
			if (curr.size() > 1) {	// find a factor combination, use >1 to avoid prime number having itself
				res.add(new ArrayList<Integer>(curr));
			}
			return;
		}
		for (int i = start; i * i <= n; i++) {	// from start to sqrt(n)
			if (n % i != 0) {
				continue;
			}
			curr.add(i);
			getFactors(res, curr, n / i, i);	// here start is i because a factor may appear multiple times
			curr.remove(curr.size() - 1);
		}
		int i = n;
		curr.add(i);
		getFactors(res, curr, n / i, i);
		curr.remove(curr.size() - 1);
	}
}