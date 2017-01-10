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
        // then construct left & right subtrees.    O(n) time, O(1) Space.
        if (nums == null || nums.length == 0) {
        	return null;
        }
        TreeNode res = toBST(nums, 0, nums.length - 1);
        return res;
    }

    // converts nums[begin] ~ nums[end] to a balanced BST and returns its root
    private TreeNode toBST(int[] nums, int begin, int end) {
    	if (begin > end) {
    		return null;
    	}
    	int mid = begin + (end - begin) / 2;
    	TreeNode node = new TreeNode(nums[mid]);
    	node.left = toBST(nums, begin, mid - 1);
    	node.right = toBST(nums, mid + 1, end);
    	return node;
    }
}