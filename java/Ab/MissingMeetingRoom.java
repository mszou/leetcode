//	给一组meeting room的meeting（每个meeting由start和end时间组成）。求出在所有输入meeting时间段内没有会议，也就是空闲的时间段。每个subarray都已经sort好.
//	举例：
//	[
//	  [[1, 3], [6, 7]],
//	  [[2, 4]],
//	  [[2, 3], [9, 12]]
//	]
//	返回
//	[[4, 6], [7, 9]].
// follow up: 求有k个room空闲的时间段，（当k==n时，同原题）

import java.util.*;
// idea: store all the critical points (start & end) of each meeting, sort them in ascending order
// then traverse all the critical points and track the number of currently occupied rooms.
public class MissingMeetingRoom {
	class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	class Point {
		int time;
		int flag;	// 1 means it's a start point, 0 means it's an end point
		Point(int time, int flag) {
			this.time = time;
			this.flag = flag;
		}
	}

	public List<Interval> findMeetingRoom(List<List<Interval>> meetings, int k) {	// at least k rooms are free
		List<Interval> res = new ArrayList<Interval>();
		int n = meetings.size();
		if (n == 0) {
			return res;
		}
		List<Point> points = new ArrayList<Point>();
		for (List<Interval> room : meetings ) {
			for (Interval meeting : room) {
				points.add(new Point(meeting.start, 1));
				points.add(new Point(meeting.end, 0));
			}
		}
		// sort all critical points, if end & start at the same time, put end first
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				if (a.time == b.time) {
					return a.flag - b.flag;
				} else {
					return a.time - b.time;
				}
			}
		});
		int start = Integer.MIN_VALUE;	// record the start of eligible interval
		int count = 0;
		for (Point p : points) {
			if (p.flag == 1) {
				count++;
			}
			if (p.flag == 0) {
				count--;
			}
			if (count <= n - k && start == Integer.MIN_VALUE) {
				start = p.time;
			}
			if (count > n - k && start != Integer.MIN_VALUE) {
				if (start != p.time) {
					res.add(new Interval(start, p.time));
					start = Integer.MIN_VALUE;
				} else {
					start = Integer.MIN_VALUE;
				}
			}
		}
		int last = points.get(points.size() - 1).time;
		if (start != Integer.MIN_VALUE && last > start) {
			res.add(new Interval(start, last));
		}
		return res;
	}
	
	public static void main(String[] args) {
		MissingMeetingRoom s = new MissingMeetingRoom();
		List<List<Interval>> input = new ArrayList<List<Interval>>();
        input.add(new ArrayList<Interval>(Arrays.asList(s.new Interval(1,3), s.new Interval(6,7))));
        input.add(new ArrayList<Interval>(Arrays.asList(s.new Interval(2,4),s.new Interval(7,8))));
        input.add(new ArrayList<Interval>(Arrays.asList(s.new Interval(2,3), s.new Interval(7,12))));
        List<Interval> res = s.findMeetingRoom(input, 2);
        for(Interval r: res) {
            System.out.println(r.start + "," + r.end);
        }
	}

}
