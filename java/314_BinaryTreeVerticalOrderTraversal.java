/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * Examples:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its vertical order traversal as:
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * Given binary tree [3,9,20,4,5,2,7],
 *     _3_
 *    /   \
 *   9    20
 *  / \   / \
 * 4   5 2   7
 * return its vertical order traversal as:
 * [
 *   [4],
 *   [9],
 *   [3,5,2],
 *   [20],
 *   [7]
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
// idea: If a node is in Column i, then its left child is in Column (i-1) and its right
// child is in Column (i+1). So nodes can be put into different column buckets and we use
// Map<column number, nodes values> to store the nodes in each column. Do BFS level order
// traversal using queue, put node and col into queue at the same time.		O(n) Time.
public class Solution {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		Queue<TreeNode> nodeQ = new LinkedList<>();
		Queue<Integer> colQ = new LiinkedList<>();
		nodeQ.add(root);
		colQ.add(0);
		int min = 0, max = 0;	// record the left and right bound of col #
		while (!nodeQ.isEmpty()) {
			TreeNode curr = nodeQ.poll();
			int col = colQ.poll();
			if (!map.containsKey(col)) {
				map.put(col, new ArrayList<Integer>());
			}
			map.get(col).add(curr.val);
			if (curr.left != null) {
				nodeQ.add(curr.left);
				colQ.add(col - 1);
				min = Math.min(min, col - 1);
			}
			if (curr.right != null) {
				nodeQ.add(curr.right);
				colQ.add(col + 1);
				max = Math.max(max, col + 1);
			}
		}
		for (int i = min; i <= max; i++) {
			res.add(map.get(i));	// add the columns in order
		}
		return res;
	}
}