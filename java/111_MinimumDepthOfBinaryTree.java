/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
    public int minDepth(TreeNode root) {
    	// idea: recursively check the depth of subtrees and choose the smaller one
        if (root == null) {
        	return 0;
        }
        if (root.left == null) {    // no left child
            return minDepth(root.right) + 1;
        } else if (root.right == null) {    // no right child
            return minDepth(root.left) + 1;
        } else {    // choose the subtree with smaller minDepth
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}