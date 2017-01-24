/**
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * For example:
 * Given binary tree,
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * return 4.
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
	// sol 1: DFS. Since java pass single variable by value while pass array by reference,
	// we use an array of length 1 to pass the count to deeper level dfs.
	public int countUnivalSubtrees(TreeNode root) {
		int[] count = new int[1];
		helper(root, count);
		return count[0];
	}

	private boolean helper(TreeNode node, int[] count) {
		if (node == null) {
			return true;
		}
		boolean left = helper(node.left, count);
		boolean right = helper(node.right, count);
		if (left && right) {
			if (node.left != null && node.val != node.left.val) {
				return false;
			}
			if (node.right != null && node.val != node.right.val) {
				return false;
			}
			count[0]++;	// subtree rooted at node is a uni-value subtree
			return true;
		}
		return false;
	}


	// sol 2: DFS, count the subtrees while checking values, using '|' operator
	int count = 0;

	public int countUnivalSubtrees(TreeNode root) {
		all(root, 0);
		return count;
	}

	// returns whether all nodes in the given tree have the given value.
	// And while doing that, it also counts the uni-value subtrees.
	private boolean all(TreeNode root, int val) {
		if (root == null) {
			return true;
		}
		// 下面用'|'而非'||'因为在前者true的情况下'||'会省略对后者的计算，而'|'不会省略
		if (!all(root.left, root.val) | !all(root.right, root.val)) {
			return false;
		}
		count++;
		return root.val == val;
	}
}