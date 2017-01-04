/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * For example,
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * Longest consecutive sequence path is 2-3, not3-2-1, so return 2.
 */

public class Solution {
	// idea: DFS, check whether left.val or right.val is the next consecutive number
	private int max = 0;	// need a global variable to record the max length

	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, 0, root.val);
		return max;
	}

	private void dfs(TreeNode node, int len, int target) {
		if (root == null) {
			return;
		}
		if (root.val == target) {
			len++;
		} else {
			len = 1;	// reset length
		}
		max = Math.max(max, len);
		dfs(root.left, len, root.val + 1);
		dfs(root.right, len, root.val + 1);
	}
}