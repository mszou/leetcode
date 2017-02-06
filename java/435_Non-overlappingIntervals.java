/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        // Equivalent to finding max non-overlapping intervals. Greedy: "earliest ddl first"
        // sort intervals in ascending end time, choose with no conflict.    O(nlogn) Time.
        if (intervals == null || intervals.length == 0) {
        	return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>(){  // sorting takes O(nlogn) Time
        	@Override
        	public int compare(Interval a, Interval b) {
        		return a.end - b.end; // in this problem, no need to consider the case of same end time
        	}
        });
        int curEnd = Integer.MIN_VALUE;	// in case of having negative time points
        int count = 0;  // # non-overlapping intervals
        for (Interval i : intervals) {
        	if (i.start >= curEnd) {
        		curEnd = i.end;
        		count++;
        	}
        }
        return intervals.length - count;    // # intervals need to be removed
    }
}