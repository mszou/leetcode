/**
 * Given a binary tree
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
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
    public void connect(TreeLinkNode root) {
        // idea: do populating level by level, for a current node, let the next pointer of left child point to right child,
        // and let the next pointer of right child point to the left child of the next right node of the current node
        TreeLinkNode levelStart = root;
        while (levelStart != null) {
        	TreeLinkNode cur = levelStart;	// current node in this level
        	while (cur != null) {
        		if (cur.left != null) {
        			cur.left.next = cur.right;
        		}
        		if (cur.right != null && cur.next != null) {
        			cur.right.next = cur.next.left;
        		}
        		cur = cur.next;
        	}
        	levelStart = levelStart.left;
        }
    }
}