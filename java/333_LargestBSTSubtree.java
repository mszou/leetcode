/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 * Note:
 * A subtree must include all of its descendants.
 * Here's an example:
 *     10
 *     / \
 *    5  15
 *   / \   \ 
 *  1   8   7
 * The Largest BST Subtree in this case is the highlighted one. 
 * The return value is the subtree's size, which is 3.
 * Hint:
 * You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
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
	// sol 1: naive, check each subtree to see if it's valid BST, and count nodes. O(n^2) Time.
	public int largestBSTSubtree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE)) {
			return count(root);
		} else {
			return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
		}
	}

	private int count (TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + count(node.left) + count(node.right);
	}

	private boolean isValid(TreeNode node, int max, int min) {
		if (node == null) {
			return true;
		}
		if (node.val < max && node.val > min) {
			return isValid(node.left, node.val, min) && isValid(node.right, max, node.val);
		} else {
			return false;
		}
	}

	// sol 2: O(n) Time. The sign of res indicates whether the returning node is root of a BST or not.
	class Result {
		int res;
		int min;
		int max;
		public Result(int res, int min, int max) {
			this.res = res;
			this.min = min;
			this.max = max;
		}
	}

	public int largestBSTSubtree(TreeNode root) {
		Result res = bstSubtree(root);
		return Math.abs(res.res);
	}
	private Result bstSubtree(TreeNode node) {
		if (node == null) {
			return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		Result left = bstSubtree(node.left);
		Result right = bstSubtree(node.right);
		if (left.res < 0 || right.res < 0 || node.val < left.max || node.val > right.min) {
			return new Result(Math.max(Math.abs(left.res), Math.abs(right.res)) * -1, 0, 0);
		} else {
			return new Result(left.res + right.res + 1, Math.min(node.val, left.min), Math.max(node.val, right.max));
		}
	}
}