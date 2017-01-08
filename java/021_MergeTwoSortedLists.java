/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// idea: always compare the heads of two lists and add the smaller one
		// until one list is finished, then add all the rest of the other list
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;	// the pointer in the new list
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				pointer.next = l1;
				l1 = l1.next;
			} else {
				pointer.next = l2;
				l2 = l2.next;
			}
			pointer = pointer.next;
		}
		if (l1 != null) {	// l1 is not finished
			pointer.next = l1;
		}
		if (l2 != null) {	// l2 is not finished
			pointer.next = l2;
		}
	}
}