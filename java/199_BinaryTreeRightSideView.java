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
        // idea: DFS, first go along right branch until reach leaf, those nodes are all in right
        // side view. then traverse left subtrees and check if there are nodes in deeper levels
        // can be seen. The size of result should be the same as the height of the tree, because
        // the i-th element in result is the right-most node in i-th level. (root is Level 0.)
        // In this way, we make sure that the first node we visit in each level is the right-most.
        List<Integer> res = new ArrayList<Integer>();
        rightView(root, res, 0);
        return res;
    }

    private void rightView(TreeNode node, List<Integer> res, int level) {
    	if (node == null) {
    		return;
    	}
    	if (res.size() == level) {	// means no node for this level yet
    		res.add(node.val);
    	}
    	rightView(node.right, res, level + 1);
    	rightView(node.left, res, level + 1);
    }
}