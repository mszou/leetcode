/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
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
	public int minMeetingRooms(Interval[] intervals) {
		// idea: sort the interval by start time, then use min-heap to track the earliest end time, compare
		// next meeting with the earliest end time, if no overlap, then no need for a new room, so merge.
		// In this way, the heap stores all conflicting meetings, where the first one has earliest end time.
		// Finally, pq.size() is exactly the min # rooms needed.	O(nlogn) Time, O(n) Space.
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, new Comparator<Interval>(){
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});	// in Java 8 can also write as: Arrays.sort(intervals, (a, b) -> (a.start - b.start));
		PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>(){
			public int compare(Interval a, Interval b) {
				return a.end - b.end;
			}
		});
		pq.offer(intervals[0]);	// start with the earlist meeting, arrange a meeting room
		for (int i = 1; i < intervals.length; i++) {
			Interval curr = pq.poll();
			if (intervals[i].start >= curr.end) {	// no overlap, so no need for a new room
				curr.end = intervals[i].end;	// merge the intervals into a room
			} else {
				pq.offer(intervals[i]);	// need a new room for this meeting
			}
			pq.offer(curr);	// put the current meeting back into the heap for sorting
		}
		return pq.size();	// all conflicting meetings need independent rooms
	}
}