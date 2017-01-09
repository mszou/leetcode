/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
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
    public ListNode rotateRight(ListNode head, int k) {
		// idea: two pointers fast & slow starting from the beginning, move fast k places forward (to the 
    	// position that original tail should be in rotated list), then move two pointers (length - k)
    	// places simultaneously, when fast reaches the end, slow points to the end node in rotated list
        // remember to consider the case that k >= length.      O(n) Time, O(1) Space.
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int len = getLength(head);	// get the length of the list
        k = k % len;	// how many moves that actually needed (if k >= len, more than one round)
        if (k == 0) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;	// first move fast k places forward
        }
        // move (len - k) places, when fast reaches the end, slow points to the end node in rotated list
        while (fast.next != null) { 
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = dummy.next;	// connect the original tail to the original head
        dummy.next = slow.next;	// let dummy(pseudo-head) point to the new head
        slow.next = null;	// slow is the end, break the link (pointing to NULL)
        return dummy.next;
    }
    
    public int getLength(ListNode head) {
        int len = 0;
        while(head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}