/**
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
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
    public int countNodes(TreeNode root) {
    	if (root == null) {
        	return 0;
        }

        // // sol 1(naive, recursive): O(n) Time.
        // return 1 + countNodes(root.left) + countNodes(root.right);

    	// sol 2 (iterative): O(logn * logn) Time. iterate logn times and computing height takes logn time
        // for each (non-leaf) node, at least one of its subtrees (left or right) is complete
    	// always add the count of the complete subtree, and then analyse the subtree on the other side
    	int count = 0, h = height(root);
    	while (root != null) {
    		// right subtree may have (h-1) or (h-2) levels
    		if (height(root.right) == h - 1) {	// means left subtree is full
    			count += 1 << h - 1;	// # nodes in left subtree + root = 2 ^ h nodes
    			root = root.right;
    		} else {	// right sub-tree is a full (h-2)-level binary tree
    			count += 1 << h - 2;	// # nodes in right subtree + root = 2 ^ (h - 1) nodes
    			root = root.left;
    		}
    		h--;
    	}
    	return count;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return height(root.left) + 1;
        }
    }
}