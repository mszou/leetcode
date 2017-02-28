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
    // sol 1: recursive.    O(n) Time, O(1) Space.
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        if (root.left != null) {
            res.addAll(inorderTraversal(root.left));
        }
        res.add(root.val);
        if (root.right != null) {
            res.addAll(inorderTraversal(root.right));
        }
        return res;
    }

    // // sol 2: iterative. use stack, first push all left children, then pop
    // // one and traverse its right sub-tree. O(n) Time, O(height) Space.
    // public List<Integer> inorderTraversal(TreeNode root) {
    //     List<Integer> res = new ArrayList<Integer>();
    //     if (root == null) {
    //         return res;
    //     }
    //     Stack<TreeNode> stack = new Stack<TreeNode>();
    //     TreeNode curr = root;
    //     // traverse the tree
    //     while (curr != null || !stack.isEmpty()) {
    //     	// push all left children
    //     	while (curr != null) {
    //     		stack.push(curr);
    //     		curr = curr.left;
    //     	}
    //     	// when no left sub-tree, pop the leaf, add to the result, and then traverse right sub-tree
    //     	curr = stack.pop();
    //     	res.add(curr.val);
    //     	curr = curr.right;
    //     }
    //     return res;
    // }

    // sol 3: Morris traversal, without recursion or stack.
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return res;
    }
}