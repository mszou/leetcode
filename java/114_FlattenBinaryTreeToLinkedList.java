/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
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
    public void flatten(TreeNode root) {
        // idea: recursively flatten the subtrees, because the order is actually same as preorder traversal,
        // we flatten in 'reversed' preorder, i.e. for a node, flatten its right subtree first, then append
        // to the flattened left subtree, together append to this node.
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode root, TreeNode prev) {
    	if (root == null) {
    		return prev;
    	}
    	prev = flatten(root.right, prev);
    	prev = flatten(root.left, prev);
    	root.left = null;
    	root.right = prev;
    	prev = root;	// update prev, now prev is a flattened subtree rooted at current root
    	return prev;
    }
}