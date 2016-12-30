/**
 * Design and implement a TwoSum class. It should support the following operations:add and find.
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */

public class TwoSum {
	// There may be numbers being added more than once, so use Map<number, freq> to store
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void add(int number) {
		map.put(number, map.getOrDefault(number, 0) + 1);
	}

	public boolean find(int value) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int addend1 = entry.getKey();
			int target = value - addend1;
			if (target == addend1 && entry.getValue() > 1) {
				return true;
			}
			if (target != addend1 && map.containsKey(target)) {
				return true;
			}
		}
		return false;
	}
}