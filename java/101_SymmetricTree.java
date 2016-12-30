/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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
	// sol 1: recursive. left subtree and right subtrees should be mirror symmetric
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
        	return true;
        }
        return isSymmetricSubtree(root.left, root.right);
    }

    private boolean isSymmetricSubtree(TreeNode left, TreeNode right) {
    	if (left == null && right == null) {
    		return true;
    	}
    	if (left == null || right == null) {
    		return false;
    	}
    	if (left.val != right.val) {
    		return false;
    	}
    	return isSymmetricSubtree(left.left, right.right) && isSymmetricSubtree(left.right, right.left);
    }

    // sol 2: non-recursive (use stack)
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	if (root.left == null || root.right == null) {
    		return root.left == root.right;
    	}
    	if (root.left.val != root.right.val) {
    		return false;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode left, right;
    	stack.push(root.left);
    	stack.push(root.right);
    	while (!stack.empty()) {
    		if (stack.size() % 2 != 0) {
    			return false;
    		}
    		right = stack.pop();
    		left = stack.pop();
    		if (left.left != null) {
    			if (right.right == null || left.left.val != right.right.val) {
    				return false;
    			}
    			stack.push(left.left);
    			stack.push(right.right);
    		} else if (right.right != null) {
    			return false;
    		}
    		if (left.right != null) {
    			if (right.left == null || left.right.val != right.left.val) {
    				return false;
    			}
    			stack.push(left.right);
    			stack.push(right.left);
    		} else if (right.left != null) {
    			return false;
    		}
    	}
    	return true;
    }
}