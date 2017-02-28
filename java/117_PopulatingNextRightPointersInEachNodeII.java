/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
	// idea: For the case that is not a perfect binary tree, need to look at two levels at the same time, keep
    // track of the curr node in current level, the head of lower level, and the previous node in lower level.
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = root;	// the left most node in the lower level
        TreeLinkNode prev = null;	// the previous node in the lower level
        TreeLinkNode curr = null;	// the current node in the upper level
        while (head != null) {
        	// start to next level: move curr pointer to head, and set prev & head as null
        	curr = head;
        	prev = null;
        	head = null;
        	while (curr != null) {
        		if (curr.left != null) {  // deal with curr.left
        			if (prev != null) {
        				prev.next = curr.left;
        			} else { // means no head in lower level yet
        				head = curr.left;
        			}
        			prev = curr.left;
        		}
        		if (curr.right != null) { // deal with curr.right
        			if (prev != null) {
        				prev.next = curr.right;
        			} else {
        				head = curr.right;
        			}
        			prev = curr.right;
        		}
        		curr = curr.next;
        	}
        }
    }
}