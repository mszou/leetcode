/**
 * Given a binary tree, find all leaves and then remove those leaves. Then repeat the previous steps until the tree is empty.
 * Example:
 * Given binary tree 
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * Returns [4, 5, 3], [2], [1].
 * Explanation:
 * 1. Remove the leaves [4, 5, 3] from the tree
 *           1
 *          / 
 *         2          
 * 2. Remove the leaf [2] from the tree
 *           1          
 * 3. Remove the leaf [1] from the tree
 *           []         
 * Returns [4, 5, 3], [2], [1].
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
	// idea: recursive, Bottom-up. we need to know the height of each node, the height of leaf is 0. The height of a node
	// is also its index in the res. helper function height(node, res) returns the height of node and adds node to res.
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		height(root, res);
		return res;
	}

	private int height(TreeNode node, List<List<Integer>> res) {
		if (node == null) {
			return -1;
		}
		int level = 1 + Math.max(height(node.left, res), height(node.right, res));
		if (res.size() < level + 1) {
			res.add(new ArrayList<>());
		}
		res.get(level).add(node.val);
		return level;
	}
}