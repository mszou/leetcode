/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTIterator {
    // idea: use stack. The smallest number in BST is the most down-left node of the tree, since it has no
    // left child, if it has right child, the next smallest is the most down-left node of the right subtree;
    // if it doesn't have right child, i.e. it is a leaf, the next smallest is its parent. Therefore, go all
    // the way down its left branch and push the nodes into the stack. It has next if the stack is not empty.
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        fillStack(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.pop();    // the most down-left node in current BST is current smallest
        if (curr.right != null) {   // has right child
            fillStack(curr.right);  // push the left descendants in right subtree onto stack
        }
        return curr.val;
    }

    // push 'node' and all its left descendants onto stack
    private void fillStack(TreeNode node) {
    	while (node != null) {
    		stack.push(node);
    		node = node.left;
    	}
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */