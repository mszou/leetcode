/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

public class LRUCache {
	// design a class of double-linked node, which has a key, a value and methods
	// removeFromHead(), update() and append() to help with the process of LRUCache
	// use a map<key, DLinkNode> to access the node of given key

	private Map<Integer, DLinkNode> cache;
	DLinkNode head = null;
	DLinkNode tail = null;
	int capacity;

	public LRUCache(int capacity) {
		cache = new HashMap<Integer, DLinkNode>();
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			DLinkNode target = cache.get(key);
			int value = target.value;
			target.update();
			return value;
		} else {
			return -1;
		}
	}

	public void set(int key, int value) {
		if (cache.containsKey(key)) {
			DLinkNode target = cache.get(key);
			target.value = value;
			target.update();
		} else {
			if (capacity == 0) {
				return;
			}
			if (cache.size() == capacity) {	// need to remove the LRU DLinkNode
				cache.remove(head.key);
				head.removeFromHead();
			}
			DLinkNode newNode = new DLinkNode(key, value);
			newNode.append();
			cache.put(key, newNode);
		}
	}


	class DLinkNode {
		int key;
		int value;
		DLinkNode left = null;
		DLinkNode right = null;
		public DLinkNode(int key, int value) {
			this.key = key;
			this.value = value;
		}

		// remove head from list and update head reference
		private void removeFromHead() {
			if (tail == this) {	// 'this' is the only node
				head = null;
				tail = null;
			} else {	// remove 'this', so this.right becomes the head
				head = this.right;
				head.left = null;
			}
		}

		private void update() {
			// no need to update if accessing the most recently used value
			if (tail == this) {
				return;
			} else {	// remove from the current position and update nodes on both sides
				if (this != head) {
					this.left.right = this.right;
				} else {
					head = this.right;
				}
				this.right.left = this.left;
				this.append();	// append 'this' to the tail
			}
		}

		private void append() {	// append a node to the tail
			if (tail == null) {	// current list is empty
				head = this;
				tail = this;
			} else {
				this.right = null;
				this.left = tail;
				tail.right = this;
				tail = this;	// update the tail reference to the new tail
			}
		}
	}
}