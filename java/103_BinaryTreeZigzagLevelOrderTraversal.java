/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
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
	// sol 1: For each level, use a LinkedList to store the nodes that already visited in this level.
	// if the current node is in odd level, insert at the beginning of the LinkedList for that level;
	// if the current node is in even level, insert at the end of the LinkedList for that level.
	// this difference was achieved within a zigzag method taking in level number (different from 102)
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		zigzag(res, root, 0);
		return res;
	}

	private void zigzag(List<List<Integer>> res, TreeNode curr, int level) {
		if (curr == null) {
			return;
		}
		if (res.size() <= level) {
			List<Integer> newLevel = new LinkedList<>();	// a LinkedList for current level
			res.add(newLevel);
		}
		List<Integer> currLevel = res.get(level);
		if (level % 2 == 0) {	// even level
			currLevel.add(curr.val);	// left to right, so insert at the end
		} else {	// odd level
			currLevel.add(0, curr.val);	// insert at the beginning
		}
		zigzag(res, curr.left, level + 1);
		zigzag(res, curr.right, level + 1);
	}

	// // sol 2: use stacks.
	// public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	// 	List<List<Integer>> res = new ArrayList<>();
	// 	if (root == null) {
	// 		return res;
	// 	}
	// 	Stack<TreeNode> currLevel = new Stack<TreeNode>();
	// 	Stack<TreeNode> nextLevel = new Stack<TreeNode>();
	// 	Stack<TreeNode> temp;
	// 	currLevel.push(root);
	// 	boolean normalOrder = true;	// normal order for even levels, reversed order for odd levels
	// 	while (!currLevel.empty()) {
	// 		List<Integer> currLevelRes = new ArrayList<Integer>();
	// 		while (!currLevel.empty()) {
	// 			TreeNode node = currLevel.pop();
	// 			currLevelRes.add(node.val);
	// 			if (normalOrder) {	// nomal order, first left node then right node
	// 				if (node.left != null) {
	// 					nextLevel.push(node.left);
	// 				}
	// 				if (node.right != null) {
	// 					nextLevel.push(node.right);
	// 				}
	// 			} else {	// reversed order, first right node then left node
	// 				if (node.right != null) {
	// 					nextLevel.push(node.right);
	// 				}
	// 				if (node.left != null) {
	// 					nextLevel.push(node.left);
	// 				}
	// 			}
	// 		}
	// 		res.add(currLevelRes);
	// 		temp = currLevel;
	// 		currLevel = nextLevel;
	// 		nextLevel = temp;
	// 		normalOrder = !normalOrder;
	// 	}
	// 	return res;
	// }
}