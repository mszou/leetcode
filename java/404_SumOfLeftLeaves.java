/**
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
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
    public int sumOfLeftLeaves(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int sum = 0;

        // sol 1: recursive
    	if (root.left != null && root.left.left == null && root.left.right == null) {
    		sum += root.left.val;	// root.left is a left leaf
    	} else {
    		sum += sumOfLeftLeaves(root.left);
    	}
    	sum += sumOfLeftLeaves(root.right);
    	return sum;

    	// sol 2: iterative, use a stack, push non-leaf nodes that may have left leaves
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while (!stack.empty()) {
    		TreeNode curr = stack.pop();
    		if (curr.left != null) {
    			if (curr.left.left == null && curr.left.right == null) {
    				sum += curr.left.val;	// curr.left is a left leaf
    			} else {
    				stack.push(curr.left);	// curr.left is not a leaf
    			}
    		}
    		if (curr.right != null) {
    			if (curr.right.left != null || curr.right.right != null) {
    				stack.push(curr.right);	// curr.right is not a leaf
    			}
    		}
    	}
    	return sum;
    }
}