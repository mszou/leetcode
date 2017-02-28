/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // idea: Divide and Conquer, 1st in preorder is root of current (sub)tree, find the position of that root
        // in inorder, then elements on its left are in the left subtree, those on its right are in right subtree.
        // optimize: use HashMap<val, index> to store the index of nodes in the inorder.   O(n) Time, O(n) Space.
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
        	return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
        	map.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    // construct (sub)tree
    private TreeNode helper(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
    	if (preStart > preorder.length - 1 || inStart > inEnd) {
    		return null;
    	}
    	TreeNode root = new TreeNode(preorder[preStart]);
    	// find the position of current root in inorder, then elements on the left is left subtree
    	int pos = map.getOrDefault(root.val, -1);
    	if (pos == -1) {
    		return null;
    	}
    	root.left = helper(preorder, preStart + 1, inorder, inStart, pos - 1, map);
    	root.right = helper(preorder, preStart + pos - inStart + 1, inorder, pos + 1, inEnd, map);
    	return root;
    }
}