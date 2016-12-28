/**
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * Example:
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * // Since 1 is the only number in the set, getRandom always return 1.
 * randomSet.getRandom();
 */

public class RandomizedSet {
	// idea: use an ArrayList to store numbers and a hashmap to store their positions in the list
	// For insertion, add that num to the list and put its pos into the map. For deletion, if the
	// target is not located at the tail of the list, swap the last one with it, then remove
	ArrayList<Integer> nums;
	HashMap<Integer, Integer> pos;
	Random rand = new Random();	// need to import java.util.*;
	/** Initialize your data structure here. */
	public RandomizedSet() {
		nums = new ArrayList<Integer>();
		pos = new HashMap<Integer, Integer>();
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (pos.containsKey(val)) {
			return false;
		}
		pos.put(val, nums.size());
		nums.add(val);
		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (!pos.containsKey(val)) {
			return false;
		}
		int idx = pos.get(val);
		if (idx < nums.size() - 1) {	// if not the last one in the list, put the last one at this position
			int last = nums.get(nums.size() - 1);
			nums.set(idx, last);
			pos.put(last, idx);
		}
		// then remove this val
		pos.remove(val);
		nums.remove(nums.size() - 1);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(rand.nextInt(nums.size()));
	}
}

/** 
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */