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
        // idea: use a queue to store the level to be traversed, each time we poll the first node
        // from the queue, add it to the list and put its children into the queue for next level.
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
        	return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int levelSize = queue.size();	// # nodes in the current level
        	List<Integer> currLevel = new LinkedList<Integer>();
        	for (int i = 0; i < levelSize; i++) {  // traverse current level
        		TreeNode curr = queue.poll();
                currLevel.add(curr.val);
                if (curr.left != null) {
        			queue.offer(curr.left);
        		}
        		if (curr.right != null) {
        			queue.offer(curr.right);
        		}
        	}
        	res.add(currLevel);
        }
        return res;
    }
}