/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
    public ListNode swapPairs(ListNode head) {
        // idea: use a pointer, swap pointer.next & pointer.next.next in place
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pointer = dummy;
        while (pointer.next != null && pointer.next.next != null) {
        	// swap
        	ListNode n1 = pointer.next, n2 = pointer.next.next;
        	pointer.next = n2;
        	n1.next = n2.next;
        	pointer.next.next = n1;
        	// move pointer to next pair
        	pointer = n1;
        }
        return dummy.next;
    }
}