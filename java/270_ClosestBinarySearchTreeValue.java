/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
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
	public int closestValue(TreeNode root, double target) {
		// idea: do regular searching for the target, i.e. compare root.val with target, and go down to the
		// corresponding subtree that a possible closer value may appear. Keep tracking of the closest value
		// so far while going down until we reach a leaf and stop searching.	O(h) Time, O(1) Space.
		int res = root.val;
		while (root != null) {
			if (Math.abs(target - root.val) < Math.abs(target - res)) {
				res = root.val;
			}
			root = root.val > target ? root.left : root.right;	// go to the closer subtree
		}
		return res;
	}
}