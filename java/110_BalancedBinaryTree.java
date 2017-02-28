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
    // a corner case: [1,2,2,3,null,null,3,4,null,null,4] not balanced with subtrees of same height
    
    // sol 1: recursive. check the heights of subtrees and their balance. O(n^2) Time
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // sol 2: DFS. O(n) Time. return the height of the current node in DFS recursion. When the
    // subtree is balanced, dfsHeight() returns its height, otherwise, returns -1. if either
    // child has a -1 dfsHeight, the parent is not balanced, either, i.e has a -1 return value.
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
        	return true;
        }
        return dfsHeight(root) != -1;
    }

    private int dfsHeight(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int left = dfsHeight(root.left);
    	int right = dfsHeight(root.right);
    	if (left == -1 || right == -1) {	// child subtree not balanced
    		return -1;
    	}
    	if (Math.abs(left - right) > 1) {	// current (sub)tree not balanced
    		return -1;
    	}
    	return Math.max(left, right) + 1;
    }
}