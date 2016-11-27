/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3) 
 * findMedian() -> 2
 */ 


public class MedianFinder {
	// idea: keep two heaps: a max-heap small stores the smaller half of the numbers,
	// while a min-heap large stores the larger half of the numbers, and make sure
	// that the size of large is always equal to or 1 larger than the size of small
	// getting the median takes O(1) time, adding a number takes O(logn) time
	PriorityQueue<Integer> small = new PriorityQueue();
	PriorityQueue<Integer> large = new PriorityQueue(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
    	// because the data stream is ordered, so no need to compare
        large.offer(num);
        small.offer(large.poll());
        if (large.size() < small.size()) {
        	large.offer(small.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (large.size() == small.size()) {	// even number
        	return (large.peek() + small.peek()) / 2.0;
        } else {
        	return (double) large.peek();
        }
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();