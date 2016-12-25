/**
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * For example:
 * Given the below binary tree,
 *        1
 *       / \
 *      2   3
 * Return 6.
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
	int maxValue;  // global variable, updated by maxDownPath

    public int maxPathSum(TreeNode root) {
        // idea: Each path must has one highest node (the lowest common ancestor of all other nodes in the path).
        // separated by this highest node, the path can be divided into 2 downward paths
    	maxValue = Integer.MIN_VALUE;
    	maxDownPath(root);
    	return maxValue;
    }

    // returns the max sum of 1-way downward path starting from the input node
    private int maxDownPath(TreeNode node) {
    	if (node == null) {
    		return 0;
    	}
    	int left = Math.max(0, maxDownPath(node.left));
    	int right = Math.max(0, maxDownPath(node.right));
    	maxValue = Math.max(maxValue, left + right + node.val);	// update maxValue if 2-way sum from the current node is greater than maxValue
    	return Math.max(left, right) + node.val;	// return the max sum of 1-way downward path from the node
    }
}