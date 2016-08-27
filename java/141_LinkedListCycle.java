/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 */


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // idea: two pointers, move "fast" 2 steps forward and "slow" 1 step at a time
        // if the Linked List has a cycle, fast and slow will meet at some point.
        if (head == null || head.next == null) {
            return false;
        }
        // version 1
        // initial positions are not important, set them different to let the while condition holds
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {	// reaches the end
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;

		// // version 2
		// ListNode fast = head;
		// ListNode slow = head;
		// while (fast.next != null && fast.next.next != null) {	// not reach the end
		// 	fast = fast.next.next;
		// 	slow = slow.next;
		// 	if (fast == slow) {
		// 		return true;
		// 	}
		// }
		// return false;
    }
}