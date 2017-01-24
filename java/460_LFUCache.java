/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * Example:
 * LFUCache cache = new LFUCache( 2 /* capacity * / )
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

public class LFUCache {
	// store all the keys with same frequency in a Node, and use 2 maps, one stores <key, value>, the other stores <key, Node>
	// Nodes are double-linked via prev & next pointers, and keys under this frequency are stored in a LinkedHashSet
	class Node {
		public Node prev, next;
		public final int count;
		public LinkedHashSet<Integer> keys = new LinkedHashSet<>();

		public Node(Node prev, Node next, int count, int key) {
			this.prev = prev;
			this.next = next;
			this.count = count;
			keys.add(key);
		}
	}

	Node head = null;
	final int capacity;
	Map<Integer, Integer> valueMap;
	Map<Integer, Node> nodeMap;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		// HashMap(int initialCapacity, float loadFactor),当HashMap.size() > 数组大小*loadFactor时会进行数组扩容
		valueMap = new HashMap<>(this.capacity, 1f);	// loadFactor is 0.75 by default
		nodeMap = new HashMap<>(this.capacity, 1f);
	}

	public int get(int key) {
		if (nodeMap.containsKey(key)) {
			increase(key);
		}
		return valueMap.getOrDefault(key, -1);
	}

	private void increase(int key) {	// move the key to the node for frequency + 1
		Node node = nodeMap.get(key);
		node.keys.remove(key);	// remove this key from list under previous node (previous freq)
		if (node.next == null) {
			node.next = new Node(node, null, node.count + 1, key);
		} else if (node.next.count == node.count + 1) {
			node.next.keys.add(key);	// add key to the list under node of count + 1
		} else {	// insert a new Node for count + 1
			Node temp = node.next;
			Node next = new Node(node, null, node.count + 1, key);
			node.next = next;
			next.next = temp;
		}
		nodeMap.put(key, node.next);
		if (node.keys.isEmpty()) {
			removeNode(node);	// remove the node if no key has this count (frequency)
		}
	}

	private void removeNode(Node node) {	// remove node from the double linked list of Node
		if (head == node) {
			head = node.next;
		} else {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
	}

	public void put(int key, int value) {
		if (this.capacity == 0) {
			return;
		}
		// HashMap.put(K, V)的返回值是之前的value，之前若没有key的mapping则返回null
		if (valueMap.put(key, value) != null) {	// there is a previous mapping for key
			increase(key);
		} else {
			if (nodeMap.size() == this.capacity) {
				removeOne();
			}
			addNew(key);
		}
	}

	private void addNew(int key) {	// add a new item to the cache
		if (head == null) {
			head = new Node(null, null, 1, key);
		} else if (head.count == 1) {
			head.keys.add(key);
		} else {
			Node newHead = new Node(null, head, 1, key);
			head.prev = newHead;
			head = newHead;
		}
		nodeMap.put(key, head);
	}

	private void removeOne() {	// remove one item from the cache
		if (head == null) {
			return;
		}
		int oldest = head.keys.iterator().next();	// choose the earliest in the keys with least frequency
		head.keys.remove(oldest);
		if (head.keys.isEmpty()) {
			removeNode(head);
		}
		nodeMap.remove(oldest);
		valueMap.remove(oldest);
	}
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */