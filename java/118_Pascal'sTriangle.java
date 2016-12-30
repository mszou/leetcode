/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */

public class Solution {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if (numRows < 1) {
			return res;
		}

		// sol 1: naive, O(n^2) Time, O(n^2) Space. Assume 1-based rowIndex, for i-th row
		// (1 < n <= numRows), the j-th(0<j<i-1) num = (j-1)th + jth num in previous row.
		List<Integer> prev = new ArrayList<Integer>();
		prev.add(1);	// the first row
		res.add(new ArrayList<Integer>(prev));
		for (int i = 2; i <= numRows; i++) {	// start from the second row
			List<Integer> curr = new ArrayList<Integer>();
			curr.add(1);
			for (int j = 1; j < prev.size(); j++) {
				curr.add(prev.get(j - 1) + prev.get(j));
			}
			curr.add(1);
			res.add(new ArrayList<Integer>(curr));
			prev = curr;
		}
		return res;

		// sol 2: O(n) space. keep only one row, every time update the numbers
		// from right to left with the sums of each pair, then add a 1 at the end
		// But list.set(index, value) may be time-consuming.
		List<Integer> row = new ArrayList<Integer>();
		for (int i = 1; i <= numRows; i++) {
			for (int j = i - 2; j >= 1; j--) {
				int temp = row.get(j - 1) + row.get(j);
				row.set(j, temp);
			}
			row.add(1);
			res.add(new ArrayList<Integer>(row));
		}
		return res;
	}
}