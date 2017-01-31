/**
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.
 * Example 1:
 * Given points = [[1,1],[-1,1]], return true.
 * Example 2:
 * Given points = [[1,1],[-1,-1]], return false.
 * Follow up:
 * Could you do better than O(n2)?
 * Hint:
 * Find the smallest and largest x-value for all points.
 * If there is a line then it should be at x = (minX + maxX) / 2.
 * For each point, make sure that it has a reflected point in the opposite side.
 */

public class Solution {
	// idea: if there is such a line, it can only be x = (minX + maxX) / 2, or x = avgX
	// so compute that avg and check if every point has a reflected point in the set.
	// When compare self-defined class other than basic types, we need to override equals(Object o)
	// and hashCode() because we want to compare the value of two objects, not the mem address.
	// * (multiply 31 is often used in overriding hashCode because 31 is an odd prime. If it were even
	// and the multiplication overflowed, information would be lost, as multiplication by 2 is equivalent
	// to shifting. The advantage of using a prime is less clear, but it is traditional. A nice property
	// of 31 is that the multiplication can be replaced by a shift and a subtraction for better
	// performance: 31 * i == (i << 5) - i. Modern VMs do this sort of optimization automatically.)
	public boolean isReflected(int[][] points) {
		if (points == null || points.length == 0) {
			return true;
		}
		int max, min, sum;
		Set<Point> set = new HashSet<>();
		max = points[0][0];
		min = max;
		for (int[] point : points) {
			int x = point[0];
			max = Math.max(max, x);
			min = Math.min(min, x);
			set.add(new Point(point[0], point[1]));
		}
		sum = max + min;
		for (int[] point : points) {
			Point ref = new Point(sum - point[0], point[1]);
			if (!set.contains(ref)) {
				return false;
			} else {
				set.remove(ref);
			}
		}
		return set.isEmpty();
	}

	class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return this.x == p.x && this.y == p.y;
		}
		@Override
		public int hashCode() {
			return x * 31 + y * 17;
		}
	}
}