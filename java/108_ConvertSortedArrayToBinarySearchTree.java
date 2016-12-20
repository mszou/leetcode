/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        // idea: recursively choose the element in the middle to be the root of current (sub)tree
        if (nums == null || nums.length == 0) {
        	return null;
        }
        TreeNode res = helper(nums, 0, nums.length - 1);
        return res;
    }

    private TreeNode helper(int[] nums, int begin, int end) {
    	if (begin > end) {
    		return null;
    	}
    	int mid = begin + (end - begin) / 2;
    	TreeNode node = new TreeNode(nums[mid]);
    	node.left = helper(nums, begin, mid - 1);
    	node.right = helper(nums, mid + 1, end);
    	return node;
    }
}