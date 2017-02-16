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
	
	// sol 1: with a global variable to record the max length
	private int max = 0;	// global max

	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, 0, root.val);
		return max;
	}

	// check whether node.val is target (if so, increase len; if not, reset len) then dfs.
	private void dfs(TreeNode node, int len, int target) {	// len is the prev len till node.parent
		if (node == null) {
			return;
		}
		if (node.val == target) {
			len++;
		} else {
			len = 1;	// not consecutive, reset length
		}
		max = Math.max(max, len);	// update max length
		dfs(node.left, len, node.val + 1);
		dfs(node.right, len, node.val + 1);
	}

	// sol 2: without global variable
	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// the following can also be written as: dfs(root, 0, root.val - 1);
		return Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
	}

	// returns the max len of consecutive sequence going through 'node' with previous length 'len' and previous value 'val'
	private int dfs(TreeNode node, int len, int val) {
		if (node == null) {
			return len;
		}
		len = (node.val - val == 1) ? len + 1 : 1;	// increase len if consecutive, otherwise reset len
		int left = dfs(node.left, len, node.val);
		int right = dfs(node.right, len, node.val);
		return Math.max(Math.max(left, right), len);
	}
}