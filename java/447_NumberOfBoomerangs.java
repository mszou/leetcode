/**
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 * Example:
 * Input:
 * [[0,0],[1,0],[2,0]]
 * Output:
 * 2
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */

public class Solution {
	public int numberOfBoomerangs(int[][] points) {
		// idea: For each point, use a Map<distance, count of pairs> to store the distances
		// to other points, if there are k points having the same distance to this point,
		// they can form k * (k - 1) boomerangs.	O(n^2) Time, O(n) Space.
		// store distance^2 instead of distance to avoid inaccuracy of double type (sqrt)
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (i == j) {
					continue;
				}
				int d = getDistance(points[i], points[j]);
				map.put(d, map.getOrDefault(d, 0) + 1);
			}
			for (int val : map.values()) {
				res += val * (val - 1);
			}
			map.clear();
		}
		return res;
	}

	// returns the square of distance between point a and point b
	private int getDistance(int[] a, int[] b) {
		return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
	}
}