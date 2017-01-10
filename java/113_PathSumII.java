/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // idea: recursive, dfs + backtracking
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(root, sum, res, path);
        return res;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
    	if (root == null) {
    		return;
    	}
    	path.add(root.val);
    	if (root.left == null && root.right == null && root.val == sum) {  // find a path
    		res.add(new ArrayList(path));
    	} else {
    		dfs(root.left, sum - root.val, res, path);
    		dfs(root.right, sum - root.val, res, path);
    	}
    	path.remove(path.size() - 1);  // backtracking
    }
}