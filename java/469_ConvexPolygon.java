/** 
 * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).
 * Note:
 * There are at least 3 and at most 10,000 points.
 * Coordinates are in the range -10,000 to 10,000.
 * You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
 * Example 1:
 * [[0,0],[0,1],[1,1],[1,0]]
 * Answer: True
 * Example 2:
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 * Answer: False
 */

public class Solution {
	public boolean isConvex(List<List<Integer>> points) {
		// idea: check the cross products. For each set of three adjacent points A,B,C, find the cross product
		// AB x BC. If the directions of all the cross products are the same, the polygon is convex.	O(n) Time.
		boolean gotNegative = false;
		boolean gotPositive = false;
		int n = points.size();
		int b, c;
		for (int a = 0; a < n; a++) {
			b = (a + 1) % n;
			c = (b + 1) % n;
			List<Integer> pA = points.get(a);
			List<Integer> pB = points.get(b);
			List<Integer> pC = points.get(c);
			int crossProduct = helper(pA.get(0), pA.get(1), pB.get(0), pB.get(1), pC.get(0), pC.get(1));
			if (crossProduct < 0) {
				gotNegative = true;
			} else if (crossProduct > 0) {
				gotPositive = true;
			}
			if (gotNegative && gotPositive) {
				return false;
			}
		}
		return true;
	}

	// return the length of cross product AB x BC in z coordinate, i.e. |AB|*|BC|*sin(theta).
	private int helper(int ax, int ay, int bx, int by, int cx, int cy) {
		// 2 vectors are: BA:(ax-bx, ay-by, 0) and BC:(cx-bx, cy-by, 0)
		return (ax - bx) * (cy - by) - (cx - bx) * (ay - by);
	}
}