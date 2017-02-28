/**
 * Reverse a singly linked list.
 * Hint:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
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
	// sol 1: iterative sol (reverse the direction of link one by one)
    public ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode newHead = null;
    	while (head != null) {
    		ListNode next = head.next;
    		head.next = newHead;
    		newHead = head;
    		head = next;
    	}
    }

    // sol 2: recursive sol
    public ListNode reverseList(ListNode head) {
    	return reverseHelper(head, null);
    }

    // reverse to let head point to newHead, i.e. 'newHead <- head'
    private ListNode reverseHelper(ListNode head, ListNode newHead) {
    	if (head == null) {
    		return newHead;
    	}
    	ListNode next = head.next;
    	head.next = newHead;
    	return reverseHelper(next, head);
    }
}