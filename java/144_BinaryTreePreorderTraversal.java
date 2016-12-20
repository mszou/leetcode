/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,2,3].
 * Note: Recursive solution is trivial, could you do it iteratively?
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *	   TreeNode left;
 * 	   TreeNode right;
 *     TreeNode(int x) { val = x; }	
 * }
 */

public class Solution {
	// sol 1: (naive, recursive) Traverse.
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		traverse(root, res);
		return res;
	}
	private void traverse(TreeNode curr, List<Integer> res) {
		if (curr == null) {
			return;
		}
		res.add(curr.val);
		traverse(curr.left, res);
		traverse(curr.right, res);
	}

	// sol 2 (non-recursion): use a stack, pop parent, push right child then left child.
	public List<Integer> preorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		stack.push(root);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			res.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return res;
	}

	// sol 3: Divide & Conquer (recursive)
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		List<Integer> left = preorderTraversal(root.left);
		List<Integer> right = preorderTraversal(root.right);
		res.add(root.val);
		res.addAll(left);
		res.addAll(right);
		return res;
	}
}