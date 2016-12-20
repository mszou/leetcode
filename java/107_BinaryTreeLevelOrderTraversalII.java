/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // idea: use a queue, the only difference from problem 102 is using res.add(0, list) to insert in the beginning
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
        	return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int levelSize = queue.size();
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
        	res.add(0, list);
        }
        return res;
    }
}