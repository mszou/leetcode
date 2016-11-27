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
    // idea: use a TreeMap. For each critical point c, get the height of the tallest rectangle over c
    // detailed explanation: https://briangordon.github.io/2014/08/the-skyline-problem.html
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ends = new ArrayList<>();
        for (int[] building : buildings) {
            ends.add(new int[] { building[0], 0, building[2] }); // Li, isRightEnd, Hi
      		  ends.add(new int[] { building[1], 1, building[2] });
        }
        Collections.sort(ends, (a, b) ->
            a[0] == b[0] ?
            (a[1] == b[1] ?
                (a[1] == 0 ? b[2] - a[2] // left end with smaller height comes after left ends with the same coordinate 
                		: a[2] - b[2]) // and vice versa for right ends 
            		: a[1] - b[1]) // right end comes after left if coordinates are the same
        		: a[0] - b[0]); // regular order when coordinates differ
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        List<int[]> collect = new ArrayList<>();
        for (int i = 0; i < ends.size(); i++) {
            int[] end = ends.get(i);
            int x = end[0];
            boolean isStart = end[1] == 0;
            int h = end[2];
            int top;
            if (isStart) {
                treemap.put(h, treemap.getOrDefault(h, 0) + 1); // enqueue left end
                top = treemap.lastKey(); // highest building
                if (h == top && treemap.get(top) == 1) { // two buildings can have the same height
                    collect.add(new int[] { x, h });
                }
            } else {
                treemap.put(h, treemap.get(h) - 1); // dequeue right end
                if (treemap.get(h) == 0) {// no building has this height anymore
                    treemap.remove(h);
                }
                if (treemap.isEmpty()) {
                    collect.add(new int[] { x, 0 });
                } else {
                    top = treemap.lastKey();
                    if (h > top) { // dequeuing gives a 2nd highest building
                        collect.add(new int[] { x, top });
                    }
                }
            }
        }
        return collect;
    }
}