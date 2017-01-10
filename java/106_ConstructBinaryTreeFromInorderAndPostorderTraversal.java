/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // idea: Divide and Conquer. last one in postorder is the root of current (sub)tree, find the position of that root
        // in inorder, then elements on its left belong to its left subtree, elements on its right belong to right subtree
        // optimize: use HashMap to store the index of nodes in the inorder.    O(n) Time, O(n) Space.
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
        	return null;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
        	map.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map) {
    	if (inStart > inEnd || postStart > postEnd) {
    		return null;
    	}
    	TreeNode root = new TreeNode(postorder[postEnd]);
    	int pos = map.getOrDefault(root.val, -1);	// pos of current root in inorder
    	if (pos == -1) {
    		return null;
    	}
    	root.left = helper(inorder, inStart, pos - 1, postorder, postStart, postStart + pos - inStart - 1, map);
    	root.right = helper(inorder, pos + 1, inEnd, postorder, postStart + pos - inStart, postEnd - 1, map);
    	return root;
    }
}