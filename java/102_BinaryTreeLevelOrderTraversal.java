/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // idea: use a queue
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
        	return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int levelSize = queue.size();	// # nodes in the current level
        	List<Integer> list = new LinkedList<Integer>();
        	for (int i = 0; i < levelSize; i++) {
        		if (queue.peek().left != null) {
        			queue.offer(queue.peek().left);
        		}
        		if (queue.peek().right != null) {
        			queue.offer(queue.peek().right);
        		}
        		list.add(queue.poll().val);
        	}
        	res.add(list);
        }
        return res;
    }
}