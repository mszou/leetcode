/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * For example, given two 1d vectors:
 * v1 = [1, 2]
 * v2 = [3, 4, 5, 6]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * Clarification for the follow up question - Update (2015-09-18):
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * It should return [1,4,8,2,5,9,3,6,7].
 */

public class ZigzagIterator {
	// idea: use a Queue to store the iterators in different vectors. Every time we call next(),
	// poll the first from the queue, take next integer and re-offer the iterator to the queue.
	// it's easy to extend to k-vector case. Another follow-up: change direction every other column, 
	// then we can change Queue to Deque and insert an empty iterator to indicate end of cycle.
	Queue<Iterator> queue;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		queue = new LinkedList<Iterator>();
		if (!v1.isEmpty()) {
			q.offer(v1.iterator());
		}
		if (!v2.isEmpty()) {
			q.offer(v2.iterator());
		}
	}

	public int next() {
		Iterator curr = queue.poll();
		int res = (int) curr.next();
		if (curr.hasNext()) {	// if the iterator becomes empty, discard it; otherwise, add it back
			q.offer(curr);
		}
		return res;
	}

	public boolean hasNext() {
		return q.peek() != null;
	}
}