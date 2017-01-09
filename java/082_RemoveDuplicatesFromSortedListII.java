/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // sol 1: use one pointer, check two following nodes (pointer.next & pointer.next.next)
        ListNode pointer = dummy;
        while (pointer.next != null && pointer.next.next != null) {
        	if (pointer.next.val == pointer.next.next.val) {	// duplicates detected
        		int val = pointer.next.val;
        		while (pointer.next != null && pointer.next.val == val) {
        			pointer.next = pointer.next.next;	// skip all duplicates
        		}
        	} else {
        		pointer = pointer.next;	// move on
        	}
        }

        // sol 2: use two pointers, 'fast' used to detect and skip duplicates,
        // 'slow' goes along all distinct numbers.	O(n) Time.
        ListNode fast = head, slow = dummy;
        while (fast != null) {
        	while (fast.next != null && fast.val == fast.next.val) {
        		fast = fast.next;	// find the last node of the duplicates
        	}
        	if (slow.next != fast) {	// there must be duplicates
        		slow.next = fast.next;	// skip duplicates
        		fast = slow.next;
        	} else {	// no duplicates here, move on
        		slow = slow.next;
        		fast = fast.next;
        	}
        }
    }
}