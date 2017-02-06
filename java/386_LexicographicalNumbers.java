/**
 * Given an integer n, return 1 - n in lexicographical order.
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */

public class Solution {
	public List<Integer> lexicalOrder(int n) {
		// idea: keep putting prev*10 as long as it <= n, when it exceeds the range, keep putting
		// prev+1 until reachs the num ending with 9 or reaches n, then we divide it by 10 and continue
		List<Integer> res = new ArrayList<>(n);
		if (n < 1) {
			return res;
		}
		res.add(1);
		for (int i = 1, prev = 1; i < n; i++) {
			if (prev * 10 <= n) {
				prev *= 10;
			} else {
				while (prev % 10 == 9 || prev == n) {
					prev /= 10;
				}
				prev++;
			}
			res.add(prev);
		}
		return res;
	}
}