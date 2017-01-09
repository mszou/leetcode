/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
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
        // idea: skip duplicates while traversing. O(n) Time.
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode pointer = head;
        while (pointer.next != null) {
        	if (pointer.val == pointer.next.val) {
        		pointer.next = pointer.next.next;	// skip duplicate
        	} else {
        		pointer = pointer.next;	// move on
        	}
        }
        return head;
    }
}