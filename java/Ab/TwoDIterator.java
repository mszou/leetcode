import java.util.*;

public class TwoDIterator {
	private Iterator<List<Integer>> rowItr;	// a row iterator
	private Iterator<Integer> eleItr;	// an element iterator within a row

	public TwoDIterator(List<List<Integer>> vec2d) {
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

	// if need to implement a romove()
	public void remove() {
		eleItr.remove();
	}

	public static void main(String[] args) {
		List<List<Integer>> vector = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
            vector.add(new ArrayList<Integer>());
        }
		vector.get(0).add(1);
		vector.get(0).add(2);
		vector.get(1).add(3);
		vector.get(3).add(5);
		TwoDIterator td = new TwoDIterator(vector);
        System.out.println(td.hasNext());
        System.out.println(td.next());
        System.out.println(td.hasNext());
        System.out.println(td.next());
        System.out.println(td.next());
        System.out.println(vector.get(1));
        td.remove();
        System.out.println(vector.get(1));
        System.out.println(td.hasNext());
        System.out.println(td.next());
//        t.remove();
        System.out.println(td.hasNext());
	}

}
