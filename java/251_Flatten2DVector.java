/**
 * Implement an iterator to flatten a 2d vector.
 * For example,
 * Given 2d vector =
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 * Hint:
 * How many variables do you need to keep track?
 * Two variables is all you need. Try with x and y.
 * Beware of empty rows. It could be the first few rows.
 * To write correct code, think about the invariant to maintain. What is it?
 * The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
 * Not sure? Think about how you would implement hasNext(). Which is more complex?
 * Common logic in two different places should be refactored into a common method.
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
import java.util.*;

public class Vector2D {
	private Iterator<List<Integer>> rowItr;	// a row iterator
	private Iterator<Integer> eleItr;	// an element iterator within a row

	public Vector2D(List<List<Integer>> vec2d) {
		if (vec2d == null || vec2d.size() == 0) {
			return;
		}
		rowItr = vec2d.iterator();
		eleItr = rowItr.next().iterator();
	}

	public int next() {
		if (hasNext()) {
			return eleItr.next();
		} else {
			throw new NoSuchElementException();
		}
	}

	public boolean hasNext() {
		// go to next row if element iterator reaches the end of the current row
		while ((eleItr == null || !eleItr.hasNext()) && rowItr.hasNext()) {
			eleItr = rowItr.next().iterator();
		}
		return eleItr != null && eleItr.hasNext();
	}

	// // if need to implement a romove()
	// public void remove() {
	// 	eleItr.remove();
	// }
}