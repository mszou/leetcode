/**
 * Sort a linked list using insertion sort.
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
	// idea: insert the nodes one by one into the new list
	// sol 1: naive. go through the entire list to find the location to insert
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode dummy = new ListNode(0);
        while (head != null) {
        	ListNode pos = dummy;
        	while (pos.next != null && pos.next.val < head.val) {
        		pos = pos.next;
        	}	// find the position to insert
        	ListNode temp = head.next;
        	head.next = pos.next;
        	pos.next = head;
        	head = temp;
        }
        return dummy.next;
    }

    // sol 2: optimized. record the values of the head & tail of the already sorted portion
    public ListNode insertionSortList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode sortedHead = head, sortedTail = head;
    	head = head.next;
    	sortedHead.next = null;
    	while (head != null) {
    		ListNode cur = head;
    		head = head.next;
    		cur.next = null;
    		if (cur.val <= sortedHead.val) {	// insert in the front
    			cur.next = sortedHead;
    			if (sortedHead.next == null) {
    				sortedTail = sortedHead;
    			}
    			sortedHead = cur;
    		} else if (cur.val >= sortedTail.val) {	// append at the tail
    			sortedTail.next = cur;
    			sortedTail = sortedTail.next;
    		} else {	// between sortedHead & sortedTail, find the position to insert
    			ListNode temp = sortedHead;
    			while (temp.next != null && temp.next.val < cur.val) {
    				temp = temp.next;
    			}
    			cur.next = temp.next;
    			temp.next = cur;
    		}
    	}
    	return sortedHead;
    }
}