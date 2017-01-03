/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */

public class MovingAverage {
	// idea: use a queue whose size is the window size to store the numbers in the window
	// while window sliding, remove the oldest number(s) to make room for new number(s).
	Queue<Integer> q;
	int windowSize;
	long sum;

	/** Initialize your data structure here. */
	public MovingAverage(int size) {
		window = new int[size];
		windowSize = size;
		sum = 0;
	}

	public double next(int val) {
		if (q.size() == windowSize) {
			sum -= q.poll();
		}
		q.offer(val);
		sum += val;
		return sum / q.size();
	}
}