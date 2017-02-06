/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * Example:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * Another valid answer is [5,2,6,null,4,null,7].
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
        // idea: recursive, find the target node, delete and return node reference of subtree
        // 3 possible cases: 1. target is leaf -> return null; 2. target only has 1 (left/right)
        // subtree -> return that subtree; 3. target has both subtrees -> find the minimum value
        // in its right subtree, replace target node with that value and delete that min value.
        // O(height) Time, (~ O(logn))
        if (root == null) {
        	return null;
        }
        if (key < root.val) {	// target in left subtree
        	root.left = deleteNode(root.left, key);
        } else if (key > root.val) {	// target in right subtree
        	root.right = deleteNode(root.right, key);
        } else {	// current node is the target
        	if (root.left == null) {
        		return root.right;
        	} else if (root.right == null) {
        		return root.left;
        	} else {   // has both subtrees
        		TreeNode minNode = findMin(root.right);
        		root.val = minNode.val;
        		root.right = deleteNode(root.right, root.val);
        	}
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
    	while (node.left != null) {
    		node = node.left;
    	}
    	return node;
    }
}