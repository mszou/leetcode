/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
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
	// idea: in-order successor of p is the smallest one among nodes whose val > p.val; In BST,
	// if root.val<=p.val, successor in right subtree; otherwise, in left subtree or root itself
	// sol 1: recursive, move root if val<=p.val, then search successor in left subtree
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		while (root != null && root.val <= p.val) {
			root = root.right;	// locate to the root whose val > p.val, so res in left or root
		}
		if (root == null) {	// means no successor for p
			return null;
		}
		TreeNode left = inorderSuccessor(root.left, p);
		if (left != null && left.val > p.val) {	// left is successor
			return left;
		} else {	// root is successor
			return root;
		}
	}

	// sol 2: iterative, update successor according to the comparison between root.val and p.val,
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		TreeNode successor = null;
		while (root != null) {
			if (p.val < root.val) {	// successor in left subtree or is root
				successor = root;
				root = root.left;
			} else {	// successor in right subtree
				root = root.right;
			}
		}
		return successor;
	}


	// follow-up: similarly, for in-order Predecessor
	// sol 1: recursive
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		while (root != null && root.val >= p.val) {
			root = root.left;	// locate to the root whose val < p.val, so res in right or root
		}
		if (root == null) {	// means no predecessor for p
			return null;
		}
		TreeNode right = inorderSuccessor(root.right, p);
		if (right != null && right.val < p.val) {	// right is predecessor
			return right;
		} else {	// root is predecessor
			return root;
		}
	}

	// sol 2: iterative
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		TreeNode predecessor = null;
		while (root != null) {
			if (p.val > root.val) {	// predecessor in right subtree or is root
				predecessor = root;
				root = root.right;
			} else {	// predecessor in left subtree
				root = root.left;
			}
		}
		return predecessor;
	}
}