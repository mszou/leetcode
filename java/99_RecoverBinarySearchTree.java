/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
	// idea: the in order traversal of a valid BST should be an ascending sequence, in which  two mispositioned elements
	// are easily to be find. The first target is the one whose value is larger than its next node, and the second target
    // is the one whose value is smaller than its previous node. When we find the first decreasing-value pair, respectively
    // set them as first and second, then update second if there is another misplaced element later

	TreeNode first = null;
	TreeNode second = null;
	TreeNode prev = new TreeNode(Integer.MIN_VALUE);	// initialize to avoid null pointer exception in the first comparison

    public void recoverTree(TreeNode root) {
        traverse(root);
        // swap to recover
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void traverse(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	traverse(root.left);
    	if (first == null && root.val <= prev.val) {   // find the first mispositioned element
    		first = prev;
    	}
    	if (first != null && root.val <= prev.val) {   // first not null, so this is the second mispositioned element
    		second = root;
    	}
    	prev = root;
    	traverse(root.right);
    }
}