/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * Hint:
 * 1. Consider implement these two helper functions:
 * 　　i. getPredecessor(N), which returns the next smaller node to N.
 * 　　ii. getSuccessor(N), which returns the next larger node to N.
 * 2. Try to assume that each node has a parent pointer, it makes the problem much easier.
 * 3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 * 4. You would need two stacks to track the path in finding predecessor and successor node separately.
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
	// naive: inorder traversal to get an array, then find the closest k value to target

	// sol 1: use two stacks for predecessors and successors.	O(n+k) Time
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> res = new ArrayList<>();
		Stack<Integer> s1 = new Stack<>();	// predecessors
		Stack<Integer> s2 = new Stack<>();	// successors
		inorder(root, target, false, s1);
		inorder(root, target, true, s2);
		while (k-- > 0) {
			if (s1.isEmpty() && s2.isEmpty()) {
				throw new NoSuchElementException("No enough values!");
			} else if (s1.isEmpty()) {
				res.add(s2.pop());
			} else if (s2.isEmpty()) {
				res.add(s1.pop());
			} else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)) {
				res.add(s1.pop());
			} else {
				res.add(s2.pop());
			}
		}
		return res;
	}

	private void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
		if (root == null) {
			return;
		}
		inorder(reverse ? root.right : root.left, target, reverse, stack);
		// early terminate, no need to traverse the whole tree
		if ((reverse && root.val <= target) || (!reverse && root.val > target)) {
			return;
		}
		// track the value of current node
		stack.push(root.val);
		inorder(reverse ? root.left : root.right, target, reverse, stack);
	}

	// sol 2: use window size k and inorder traversal, O(n) Time with early termination.
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		LinkedList<Integer> res = new LinkedList<>();
		collect(root, target, k, res);
		return res;
	}

	public void collect(TreeNode root, double target, int k, LinkedList<Integer> res) {
		if (root == null) {
			return;
		}
		collect(root.left, target, k, res);
		if (res.size() == k) {
			// if size k, add current and remove head if it's optimal, otherwise return
			if (Math.abs(target - root.val) < Math.abs(target, res.peekFirst())) {
				res.removeFirst();
			} else return;
		}
		res.add(root.val);
		collect(root.right, target, k, res);
	}
}