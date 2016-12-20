/**
 * Invert a binary tree.
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
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
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		// sol 1: recursive
		TreeNode temp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(temp);
		return root;

		// sol 2: use a stack, swap left and right subtrees, push these two children and later pop as parent
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode curr = stack.pop();
			TreeNode temp = curr.left;
			curr.left = curr.right;
			curr.right = temp;
			if (curr.left != null) {
				stack.push(curr.left);
			}
			if (curr.right != null) {
				stack.push(curr.right);
			}
		}
		return root;

		// sol 3: BFS (level order traversal)
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			TreeNode temp = curr.left;
			curr.left = curr.right;
			curr.right = temp;
			if (curr.left != null) {
				queue.offer(curr.left);
			}
			if (curr.right != null) {
				queue.offer(curr.right);
			}
		}
		return root;
	}
}