/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // idea: DP solution (from bottom to top), return the minimum sum
        // if "int[][] triangle", see http://www.jiuzhang.com/solutions/triangle/
        if (triangle.size() == 0) {
            return 0;
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {    //start from the second last row
            for (int j = 0; j <= i; j++) {
                List<Integer> nextRow = triangle.get(i + 1);
                int sum = Math.min(nextRow.get(j), nextRow.get(j + 1)) + triangle.get(i).get(j);    //add the smaller 'child'
                triangle.get(i).set(j, sum);	// update row i: put the min sums in row i
            }
        }
        return triangle.get(0).get(0);
    }
}