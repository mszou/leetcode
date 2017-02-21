/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */


/**
 * Definition for an interval
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
	public List<Interval> merge(List<Interval> intervals) {
		// idea: sort the intervals in ascending start time, then traverse and merge intervals
		// that start before previous interval ends.	O(nlogn) Time, O(1) Space.
		List<Interval> res = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0) {
			return res;
		}
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		Interval last = intervals.get(0);	// keep tracking of the last interval so far
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (curr.start <= last.end) {	// has overlap, so merge curr to last
				last.end = Math.max(last.end, curr.end);
			} else {	// intervals after curr will not have overlap with last
				res.add(last);
				last = curr;
			}
		}
		res.add(last);	// remember to add this last interval!
		return res;
	}
}