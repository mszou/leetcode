/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
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
    public boolean isBalanced(TreeNode root) {
    	// check the heights of EVERY subtree, remember to consider the case that left and right
    	// subtrees have same height while not balanced, e.g. [1,2,2,3,null,null,3,4,null,null,4]
        if (root == null) {
        	return true;
        }
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int left = getHeight(root.left);
    	int right = getHeight(root.right);
    	if (left == -1 || right == -1) {	// child subtree not balanced
    		return -1;
    	}
    	if (Math.abs(left - right) > 1) {	// current (sub)tree not balanced
    		return -1;
    	}
    	return Math.max(left, right) + 1;
    }
}