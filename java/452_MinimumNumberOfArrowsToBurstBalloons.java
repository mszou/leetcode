/**
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 * Example:
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * Output:
 * 2
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */

public class Solution {
    public int findMinArrowShots(int[][] points) {
        // idea: Greedy. sort the balloons in ascending left bound (ascending right bound if left same),
        // traverse balloons and track the right bound of the position we can shoot the current arrow.
        // O(nlogn) Time, O(1) Space.
        if (points == null || points.length == 0 || points[0].length == 0) {
        	return 0;
        }
        Arrays.sort(points, new Comparator<int[]>(){
        	@Override
        	public int compare(int[] a, int[] b) {
        		if (a[0] == b[0]) {
        			return a[1] - b[1];
        		} else {
        			return a[0] - b[0];
        		}
        	}
        });
        int arrowRight = points[0][1], count = 1;   // take the right bound of first balloon
        for (int i = 1; i < points.length; i++) {
        	int[] p = points[i];
        	if (p[0] <= arrowRight) {
        		arrowRight = Math.min(arrowRight, p[1]);
        	} else {   // balloons from here cannot be burst by current arrow
        		count++;
        		arrowRight = p[1];
        	}
        }
        return count;
    }
}