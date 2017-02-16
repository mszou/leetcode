/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * For example:
 * Given BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * return [2].
 * Note: If a tree has more than one mode, you can return them in any order.
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
	// sol 1: inorder traverse the tree and count.	O(n) Time, O(n) Space.
	Map<Integer, Integer> map;
	int max = 0;
	public int[] findMode(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		this.map = new HashMap<>();
		inorder(root);
		List<Integer> list = new LinkedList<>();
		for (int key : map.keySet()) {
			if (map.get(key) == max) {
				list.add(key);
			}
		}
		int[] res = new int[list.size()];
		list.toArray(res);
		return res;
	}

	private void inorder(TreeNode node) {
		if (node.left != null) {
			inorder(node.left);
		}
		map.put(node.val, map.getOrDefault(node.val, 0) + 1);
		max = Math.max(max, map.get(node.val));
		if (node.right != null) {
			inorder(node.right);
		}
	}

	// sol 2: follow-up, O(1) Space.
	private int currVal;
	private int currCount = 0;
	private int maxCount = 0;
	private int modeCount = 0;
	private int[] modes;

	public int[] findMode(TreeNode root) {
		inorder(root);
		modes = new int[modeCount];
		modeCount = 0;
		currCount = 0;
		inorder(root);
		return modes;
	}

	private void inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		handleValue(root.val);
		inorder(root.right);
	}

	private void handleValue(int val) {
		if (val != currVal) {
			currVal = val;
			currCount = 0;
		}
		currCount++;
		if (currCount > maxCount) {
			maxCount = currCount;
			modeCount = 1;
		} else if (currCount == maxCount) {
			if (modes != null) {
				modes[modeCount] = currVal;
			}
			modeCount++;
		}
	}
}