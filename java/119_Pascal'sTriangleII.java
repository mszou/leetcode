/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */

public class Solution {
	public List<Integer> getRow(int rowIndex) {
		// here the rowIndex is 0-based, i.e. the first row [1] is Row 0
        List<Integer> res = new ArrayList<Integer>();
		if (rowIndex < 1) {
			return res;
		}
		for (int i = 1; i <= rowIndex; i++) {
			for (int j = i - 1; j >= 1; j--) {
				int temp = res.get(j - 1) + res.get(j);
				res.set(j, temp);
			}
			res.add(1);
		}
		return res;
	}
}