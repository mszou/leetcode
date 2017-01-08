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
		// idea: sort the intervals in ascending starting point, then merge if have overlap
		if (intervals == null || intervals.size() == 0) {
			return intervals;
		}
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		List<Interval> res = new ArrayList<Interval>();
		Interval last = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			if (cur.start <= last.end) {	// can merge
				last.end = Math.max(last.end, cur.end);
			} else {
				res.add(last);
				last = cur;
			}
		}
		res.add(last);
		return res;
	}
}