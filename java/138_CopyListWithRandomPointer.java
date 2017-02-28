/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
	// sol 1: use a map to store the mapping of nodes in original list and copied list.
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode pre = dummy, newNode;
		while (head != null) {
			// copy next pointer
			if (map.containsKey(head)) {
				newNode = map.get(head);
			} else {
				newNode = new RandomListNode(head.label);
				map.put(head, newNode);
			}
			pre.next = newNode;
			// copy random pointer
			if (head.random != null) {	// remember to check null
				if (map.containsKey(head.random)) {
					newNode.random = map.get(head.random);
				} else {
					newNode.random = new RandomListNode(head.random.label);
					map.put(head.random, newNode.random);
				}
			}
			pre = newNode;
			head = head.next;
		}
		return dummy.next;
	}

	// sol 2: no HashMap, iterate 2 rounds to respectively create nodes & assign values for random pointers
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode iter = head, next;
		// First round: make copy of each node, and link them together side-by-side in a single list.
		while (iter != null) {
			next = iter.next;
			RandomListNode copy = new RandomListNode(iter.label);// let copy replace iter so that iter can move on to next
			iter.next = copy;   // n1(head)   n2   .. nk(iter)  next(iter)->..->n_end
			copy.next = next;   //       \/  ^ \/ ^         \/  ^
			iter = next;        //       copy1  copy2  ...  copyk
		}
		// Second round: assign random pointers for the copy nodes.
		iter = head;
		while (iter != null) {
			if (iter.random != null) {
				iter.next.random = iter.random.next;
			}
			iter = iter.next.next;
		}
		// Third round: restore the original list, and extract the copy list.
		iter = head;
		RandomListNode pseudoHead = new RandomListNode(0);
		RandomListNode copy, copyIter = pseudoHead;
		while (iter != null) {
			next = iter.next.next;
			// extract the copy
			copy = iter.next;
			copyIter.next = copy;
			copyIter = copy;
			// restore the original list
			iter.next = next;
			iter = next;
		}
		return pseudoHead.next;
	}
}