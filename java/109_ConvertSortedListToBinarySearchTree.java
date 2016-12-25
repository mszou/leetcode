/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	// sol 1: straight forward, recursive, always take the middle node as root
    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    private TreeNode sortedListToBST(ListNode start, ListNode end) {
    	if (start == null || start == end) {
    		return null;
    	}
    	ListNode fast = start;
    	ListNode slow = start;
    	// find middle node
    	while (fast.next != end && fast.next.next != end) {
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	TreeNode root = new TreeNode(slow.val);
    	root.left = sortedListToBST(start, slow);
    	root.right = sortedListToBST(slow.next, end);
    	return root;
    }

    // sol 2: optimized, divide and conquer, recursively break the list into two halves
    public TreeNode sortedListToBST(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	ListNode fast = head, slow = head;
    	ListNode prev = null;
    	while (fast.next != end && fast.next.next != null) {
    		fast = fast.next.next;
    		prev = slow;
    		slow = slow.next;
    	}
    	if (prev != null) {
    		prev.next = null;	// break the linked list into two
    	} else {	// the length of list is 1 or 2
    		head = null;
    	}
    	// construct two subtrees separately
    	TreeNode root = new TreeNode(slow.val);
    	root.left = sortedListToBST(head);
    	root.right = sortedListToBST(slow.next);
    	return root;
    }
}