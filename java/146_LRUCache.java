/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * Example:
 * LRUCache cache = new LRUCache( 2 /* capacity * / );
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

public class LRUCache {
	// design a class of double-linked Node, which has key, value, left Node and right Node.
	// We link the Nodes in the recency of usage, i.e. LRU in the head and MRU in the tail.
	// use a map<key, Node> to access the Node of given key. When access (get or put) a key,
	// this Node becomes recently used so we remove the Node from list and append it to tail.
	// When cache size is going to exceed capacity, remove LRU (head Node) from the list.
	// update, remove, append are all 0(1) operations in DLink list, so get & set O(1) Time.
	class Node {
		int key;
		int value;
		Node left = null;
		Node right = null;
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private Map<Integer, Node> cache;
	Node head = null;
	Node tail = null;
	int capacity;

	public LRUCache(int capacity) {
		cache = new HashMap<Integer, Node>();
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			Node target = cache.get(key);
			int value = target.value;
			update(target);
			return value;
		} else {
			return -1;
		}
	}

	private void update(Node n) {
		// no need to update if accessing the most recently used value
		if (tail == n) {
			return;
		} else {	// remove from the current position and update nodes on both sides
			if (n != head) {	// this node is not the least recent used
				n.left.right = n.right;
			} else {
				head = n.right;
			}
			n.right.left = n.left;
			append(n);	// append this node to the tail
		}
	}

	private void append(Node n) {	// append a node to the tail
		if (tail == null) {	// current list is empty
			head = n;
			tail = n;
		} else {
			n.right = null;
			n.left = tail;
			tail.right = n;
			tail = n;	// update the tail reference to the new tail
		}
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			Node target = cache.get(key);
			target.value = value;
			update(target);
		} else {
			if (capacity == 0) {
				return;
			}
			if (cache.size() == capacity) {	// need to remove the LRU Node
				cache.remove(head.key);
				removeHead(head);
			}
			Node newNode = new Node(key, value);
			append(newNode);
			cache.put(key, newNode);
		}
	}

	private void removeHead(Node n) {	// remove head from list and update head reference
		if (tail == n) {	// this node is the only one in the list
			head = null;
			tail = null;
		} else {	// remove current head n, so n.right becomes the new head
			head = n.right;
			head.left = null;
		}
	}
}