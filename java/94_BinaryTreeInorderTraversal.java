/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> inorderTraversal(TreeNode root) {
        // idea: use a stack, first push all left child until no left sub-tree, then pop and traverse right sub-tree
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        // traverse the tree
        while (curr != null || !stack.isEmpty()) {
        	// push all left child
        	while (curr != null) {
        		stack.push(curr);
        		curr = curr.left;
        	}
        	// when no left sub-tree, pop the leaf, add to the result, and then traverse right sub-tree
        	curr = stack.pop();
        	res.add(curr.val);
        	curr = curr.right;
        }
        return res;
    }
}