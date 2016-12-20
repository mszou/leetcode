/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [3,2,1].
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
	// sol 1: (naive, recursion) ver 1:
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
        	return res;
        }
        res.addAll(postorderTraversal(root.left));
        res.addAll(postorderTraversal(root.right));
        res.add(root.val);
        return res;
    }

    // sol 1: (naive, recursion) ver 2:
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        traverse(root.left, res);
        traverse(root.right, res);
        res.add(node.val);
    }

    // sol 2: (non-recursion) use a stack.
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	if (root == null) {
    		return res;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode prev = null;	// previously traversed node
    	stack.push(root);

        // The commented part leads to Time Limit Exceed
    	// while (!stack.empty()) {
     //        TreeNode curr = stack.peek();
     //        // go down the tree. check if current node is leaf,
     //        // if so, process it and pop stack; otherwise, keep going down
    	// 	if (prev == null || prev.left == curr || prev.right == curr) {
     //            // prev == null is the situation for the root node
    	// 		if (curr.left != null) {
    	// 			stack.push(curr.left);
    	// 		} else if (curr.right != null) {
    	// 			stack.push(curr.right);
    	// 		} else {
     //                stack.pop();
     //                res.add(curr.val);
     //            }
     //        // go up the tree from left node, need to check if there is a right child
     //        // if yes, push it to stack; otherwise, process parent and pop stack
    	// 	} else if (curr.left == prev) {
    	// 		if (curr.right != null) {
    	// 			stack.push(curr.right);
    	// 		}
     //        // go up the tree from right node, after coming back from right node, process parent node and pop stack.
    	// 	} else if (curr.right == prev) {
     //            stack.pop();
     //            res.add(curr.val);
     //        }
    	// 	prev = curr;
    	// }

        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if (temp.left == null && temp.right == null) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
            } else {
                if(temp.right != null) {
                    stack.push(temp.right);
                    temp.right = null;
                }
                if(temp.left != null) {
                    stack.push(temp.left);
                    temp.left = null;
                }
            }
        }
    	return res;
    }
}