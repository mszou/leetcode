/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * For example:
 * Given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1 
 */

// an intermediate graph to help understanding:
//     1
//    / 
//   2 - 3
//  / 
// 4 - 5

// 1. for a parent，有right child必有left child。而且right node只能是叶子，不会有子树。
// 2. 每一组(root, left, right)三node组在新树中：left变成root，right变成left，root变成right。
// 3. 对于(1 2 3)来说，需要在以2为根的子树2 4 5建立成新树4 5 2后，插入到新树的最右节点2下面。

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	// sol 1: recursive. left child becomes root,right child becomes left, root becomes right child.
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = upsideDownBinaryTree(root.left);
		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}

	// sol 2: iterative.
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		TreeNode curr = root;
		TreeNode next = null;
		TreeNode temp = null;
		TreeNode prev = null;
		while (curr != null) {
			next = curr.left;
			// swap nodes, temp stores the previous right child
			curr.left = temp;
			temp = curr.right;
			curr.right = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
}