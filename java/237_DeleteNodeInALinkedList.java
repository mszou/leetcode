/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
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
    public void deleteNode(ListNode node) {
    	// idea: just assign next.val to this node and let the pointer point to next.next
    	if (node == null || node.next) {
    		return;
    	}
    	node.val = node.next.val;
    	node.next = node.next.next;
    	return;
    }
}