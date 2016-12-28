/**
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 * Example:
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 */

public class RandomizedCollection {
	// idea: Instead of mapping number to index, we map number to a queue of indices because duplicates are allowed.
	// always remove the last occurance of the target value by keeping a max-heap of positions for each value, (swap with
	// the last number if necessary). If that's the only one, also remove the value in the map

    ArrayList<Integer> nums;
    HashMap<Integer, PriorityQueue<Integer>> pos;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<Integer>();
        pos = new HashMap<Integer, PriorityQueue<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
    	boolean contain = pos.containsKey(val);
        if (!contain) {
        	pos.put(val, new PriorityQueue(Collections.reverseOrder()));
        }
        pos.get(val).add(nums.size());
        nums.add(val);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!pos.containsKey(val)) {
        	return false;
        }
        int idx = pos.get(val).poll();
        if (idx != nums.size() - 1) {
        	int last = nums.get(nums.size() - 1);
        	nums.set(idx, last);
        	pos.get(last).poll();
        	pos.get(last).offer(idx);
        }
        nums.remove(nums.size() - 1);
        if (pos.get(val).isEmpty()) {
        	pos.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(new Random().nextInt(nums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */