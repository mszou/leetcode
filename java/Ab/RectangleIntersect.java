// You have a plane with lots of rectangles on it, find out how many of them intersect

import java.util.*;
public class RectangleIntersect {
	// sol 1: For each pair of rectangles, check whether they intersect each other.
	// No need to sort, O(n^2) Time.
	
	// sol 2: swipe line. sort all critical (start & end) points by x coordinate and then
	// scan, every time we encounter a x start, add its range in y to the set and check
	// whether the new rectangle has intersection. Keep counting # rectangles intersect.
	// O(2nlog2n) Time, each rectangle has 2 x coordinates for sorting.
	class Rect {
        double minX;
        double minY;
        double maxX;
        double maxY;
        Rect(double minX, double minY, double maxX, double maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(!(o instanceof Rect)) return false;
            Rect r = (Rect) o;
            if(this.minX == r.minX && this.minY == r.minY && this.maxX == r.maxX && this.maxY == r.maxY) return true;
            else return false;
        }

        @Override
        public int hashCode() {
            return (int) minX * 31 + (int) minY * 30 + (int) maxX * 29 + (int) maxY * 28;
        }
    }

    class Point implements Comparable<Point>{
        boolean isX;
        int flag;  // 1  start  0 end
        double point;
        Rect rect;
        Point(boolean isX, double point, Rect rect, int flag) {
            this.isX = isX;
            this.point = point;
            this.rect = rect;
            this.flag = flag;
        }

        public int compareTo(Point p) {
            if(this.point < p.point) return -1;
            else if(this.point > p.point) return 1;
            else {
                if(this.flag == p.flag && this.rect.equals(p.rect)) {
                    return 0;
                }
                else if(this.flag == 1) return 1;
                else return -1;
            }
        }

    }

    Set<Rect> global = new HashSet<>();
    public int countIntersect(Rect[] rects) {
        TreeSet<Point> pointX = new TreeSet<>();
        TreeSet<Point> pointY = new TreeSet<>();
        for(int i = 0; i < rects.length; i++) {
            Rect rr = rects[i];
            pointX.add(new Point(true, rr.minX, rr, 1));
            pointX.add(new Point(true, rr.maxX, rr, 0));
        }
        int result = 0;
        for(Point p : pointX) {
            if(p.flag == 1) {
                helper(pointY, p.rect.minY, p.rect.maxY, p.rect);
                pointY.add(new Point(false, p.rect.minY, p.rect, 1));
                pointY.add(new Point(false, p.rect.maxY, p.rect, 0));
            }
            else {
                Point td1 = new Point(false, p.rect.minY, p.rect, 1);
                pointY.remove(td1);
                pointY.remove(new Point(false, p.rect.maxY, p.rect, 0));
            }
        }
        return global.size();
    }

    public void helper(TreeSet<Point> pointY, double start, double end, Rect r) {
        Set<Rect> temp = new HashSet<>();
        for(Point p : pointY) {
            if(p.point <= start) {
                if(p.flag == 1) {
                    temp.add(p.rect);
                }
                else {
                    temp.remove(p.rect);
                }
            }
            else if(p.point > start && p.point < end) {
                if(p.flag == 1) {
                    temp.add(p.rect);
                }
            }
            else continue;
        }
        if(temp.size() != 0) temp.add(r);
        global.addAll(temp);
    }
	
	public static void main(String[] args) {
		RectangleIntersect s = new RectangleIntersect();
        Rect [] rects = {
//                new Rect(0,0,5,5),
//                new Rect(1,1,4,4),
//                new Rect(3,-1,5,4),
//                new Rect(2,-5,4,0),
//                new Rect(1,-6,3,-4),
//                new Rect(4,-4,5,-2),
//                new Rect(-5,-4,-2,2),
                s.new Rect(-5,-2,-2,2),
                s.new Rect(-3,1,-1,3),
                s.new Rect(1,-6,3,-4),
                s.new Rect(2,-5,4,0),
                s.new Rect(4,-4,5,-2),
                s.new Rect(3,-1,5,4),
                s.new Rect(1,1,4,4),
                s.new Rect(2,3,5,6),
                s.new Rect(4,1,5,3),
                s.new Rect(5,0,6,1),
        };
        System.out.println(s.countIntersect(rects));
	}
	
	// sol 3: Union find with path compression, can get different subsets.	O(n^2logn) Time

}
