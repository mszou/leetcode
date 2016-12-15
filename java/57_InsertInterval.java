/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // idea: Since intervals are sorted and non-overlapping, just check the start and end points of each interval,
        // directly add into res if no overlap with newInterval, merge them if have overlap
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
        	res.add(newInterval);
        	return res;
        }
        if (newInterval == null) {
        	return intervals;
        }
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {  // non-overlapping intervals at left
        	res.add(intervals.get(i));
        	i++;
        }
        // do the merge to intervals having overlap with newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        	newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        	i++;
        }
        res.add(newInterval);
        while (i < intervals.size()) {  // non-overlapping intervals at right
        	res.add(intervals.get(i));
        	i++;
        }
        return res;


        if (intervals == null || newInterval == null) {
            return intervals;
        }
        List<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos ++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {    // overlap
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        // insert the new interval
        results.add(insertPos, newInterval);
        return results;
    }
}