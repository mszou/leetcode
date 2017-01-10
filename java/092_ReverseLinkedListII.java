/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
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
	public ListNode reverseBetween(ListNode head, int m, int n) {
		// idea: use pointers to reverse links one by one between m and n. O(n) Time, O(1) Space.
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pointer = dummy;
		for (int i = 0; i < m - 1; i++) {
			pointer = pointer.next;	// move pointer to the (m-1)-th position
		}
		ListNode before_m = pointer;
		ListNode mNode = pointer.next;
		ListNode nNode = mNode;
		ListNode after_n = nNode.next;
		for (int i = m; i < n; i++) {	// reverse the link (between nNode & after_n) one by one till n
			ListNode temp = after_n.next;
			after_n.next = nNode;
			nNode = after_n;
			after_n = temp;
		}
		before_m.next = nNode;
		mNode.next = after_n;
		return dummy.next;
	}
}