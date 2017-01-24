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
	// idea: insertion sort: insert the nodes one by one into the new list

	// sol 1: naive, go through the entire list to find the location to insert
    // O(n^2) Time (Best O(n), worst O(n^2)), O(1) Space.
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode dummy = new ListNode(0);   // leads the sorted list
        while (head != null) {  // check where to insert the node 'head'
        	ListNode pointer = dummy;
        	while (pointer.next != null && pointer.next.val < head.val) {
        		pointer = pointer.next;
        	}	// find the position (between pointer & pointer.next) to insert
        	ListNode temp = head.next;
        	head.next = pointer.next;
        	pointer.next = head;   // insert 'head' between 'pointer' & 'pointer.next'
        	head = temp;   // move to next node that needs to be inserted
        }
        return dummy.next;
    }

    // sol 2: optimized, record the values of the head & tail of the already sorted portion
    public ListNode insertionSortList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode sortedHead = head, sortedTail = head; // insert the head first
    	head = head.next;
    	sortedHead.next = null;
    	while (head != null) {
    		ListNode curr = head;
    		head = head.next;
    		curr.next = null; // take out the current node from original list
    		if (curr.val <= sortedHead.val) {	// insert in the front
    			curr.next = sortedHead;
    			if (sortedHead.next == null) {   // only appears in first round
    				sortedTail = sortedHead;
    			}
    			sortedHead = curr;
    		} else if (curr.val >= sortedTail.val) {	// append at the tail
    			sortedTail.next = curr;
    			sortedTail = sortedTail.next;
    		} else {	// between sortedHead & sortedTail, find the position to insert
    			ListNode temp = sortedHead;
    			while (temp.next != null && temp.next.val < curr.val) {
    				temp = temp.next;
    			}
    			curr.next = temp.next;
    			temp.next = curr;
    		}
    	}
    	return sortedHead;
    }
}