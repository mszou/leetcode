/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // idea: use two pointers (fast & slow)
        // first move the fast pointer n places forward, then move both pointers at the same speed.
        // when the fast pointer reaches the end, the slow pointer will point to the position to remove
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        // move the fast pointer n places forward
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {	// the length of list is less than n
                return null;
            }
            fast = fast.next;
        }
        // move both pointers (length - n) places simultaneously
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // remove (skip) the target node, i.e. 'slow.next'
        slow.next = slow.next.next;
        return dummy.next;
    }
}