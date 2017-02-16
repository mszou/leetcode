/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * Notes:
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */


public class Solution {
    // idea: scanning line. Sort all the critical(start/end) points, when two points at same pos,
    // start goes before end; if both start, higher comes first; if both end, lower comes first.
    // Traverse points and use TreeMap<height, count> (O(logn) add/remove) to maintain the heights
    // of buildings over current position. treemap.lastKey() gets the highest key in this map.
    // * detailed explanation: https://briangordon.github.io/2014/08/the-skyline-problem.html
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {  // add start & end points into list
            points.add(new int[]{building[0], 0, building[2]});    // pos, notRightEnd, height
      		points.add(new int[]{building[1], 1, building[2]});    // pos, isRightEnd, height
        }
        Collections.sort(points, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) { // at same position
                    if (a[1] == 0 && b[1] == 0) {   // both are left ends
                        return b[2] - a[2]; // larger height comes before smaller height
                    } else if (a[1] == 1 && b[1] == 1) {    // both are right ends
                        return a[2] - b[2]; // smaller height comes before larger height
                    } else {    // one is left end and the other is right end
                        return a[1] - b[1];    // left end comes before right end
                    }
                } else {    // at different positions, so smaller index first
                    return a[0] - b[0];
                }
            }
        });
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            int[] curr = points.get(i);
            int x = curr[0];
            boolean isStart = curr[1] == 0;
            int h = curr[2];
            int top;
            if (isStart) {
                treemap.put(h, treemap.getOrDefault(h, 0) + 1); // put this building into treemap
                top = treemap.lastKey(); // the currently highest key, i.e. highest building
                if (h == top && treemap.get(top) == 1) { // h is the newly highest
                    res.add(new int[]{x, h});
                }
            } else {    // is right end
                treemap.put(h, treemap.get(h) - 1); // remove this building from treemap
                if (treemap.get(h) == 0) {  // no other building has this height
                    treemap.remove(h);
                }
                if (treemap.isEmpty()) {    // no more buildings at this point
                    res.add(new int[]{x, 0});
                } else {
                    top = treemap.lastKey();    // the currently highest
                    if (h > top) { // h was the highest, so decrese to the current top
                        res.add(new int[]{x, top});
                    }
                }
            }
        }
        return res;
    }
}