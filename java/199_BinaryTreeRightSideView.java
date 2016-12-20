/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * You should return [1, 3, 4].
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
    public List<Integer> rightSideView(TreeNode root) {
        // idea: DFS, first go right branch all the way to leaf, they are all in right side view
        // then traverse other left subtrees and check if there are deeper levels
        List<Integer> res = new ArrayList<Integer>();
        rightView(root, res, 0);
        return res;
    }

    private void rightView(TreeNode node, List<Integer> res, int level) {
    	if (node == null) {
    		return;
    	}
    	if (res.size() == level) {	// means no value for this level yet
    		res.add(node.val);
    	}
    	rightView(node.right, res, level + 1);
    	rightView(node.left, res, level + 1);
    }
}