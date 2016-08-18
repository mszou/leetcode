/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    public int maxPoints(Point[] points) {
        // idea: take one point as reference and check the slope for each pair of points: (y2-y1)/(x2-x1)
        // use a hashmap to record the points for each slope
        if (points == null || points.length == 0) {
            return 0;
        }
        // HashMap<slope, count>
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int max = 1;
        for (int i = 0; i < points.length - 1; i++) {   // take points[i] as reference (shared point)
            // shared point changed, map should be cleared and serve the new point
            map.clear();
            map.put(Double.MIN_VALUE, 1);  // put itself in the map and use MIN_VALUE to represent the slope
            int dup = 0;    // record the number of duplicates on points[i], initialized as 0
            double slope;
            for (int j = i + 1; j < points.length; j++) {	// check every following points
                // compute the slope
                if (points[j].x == points[i].x) {
                    if (points[j].y == points[i].y) {   // same point
                        dup++;
                        continue;
                    } else {
                        slope = Double.MAX_VALUE;   // use MAX_VALUE to represent infinity
                    }
                } else {
                    // use 0.0+(-0.0)=0.0 to solve 0.0 !=-0.0 problem (because 0.0/-1.0 = -0.0)
                    slope = 0.0 + (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                }
                if (map.containsKey(slope)) {
                    map.put(slope, map.get(slope) + 1);
                } else {
                    map.put(slope, 2);
                }
            }
            // update max
            for (int val : map.values()) {
                max = Math.max(max, (val + dup));
            }
        }
        return max;
    }
}