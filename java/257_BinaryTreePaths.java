/**
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
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
    public List<String> binaryTreePaths(TreeNode root) {
        // idea: recursive DFS + back-tracking, use StringBuilder to generate strings
        List<String> res = new ArrayList<String>();
        if (root == null) {
        	return res;
        }
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    // continue DFS from current node 'node' with the StringBuilder 'sb'
    private void helper(List<String> res, TreeNode node, StringBuilder sb) {
    	if (node == null) {
    		return;
    	}
    	int len = sb.length();
    	sb.append(node.val);
    	if (node.left == null && node.right == null) { // reach a leaf
    		res.add(sb.toString());   // add this path
    	} else {
    		sb.append("->");
    		helper(res, node.left, sb);
    		helper(res, node.right, sb);
    	}
    	sb.setLength(len);	// back-tracking
    }
}