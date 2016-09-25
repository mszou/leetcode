/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * Hint:
 * 1. Try to utilize the property of a BST.
 * 2. What if you could modify the BST node's structure?
 * 3. The optimal runtime complexity is O(height of BST).
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
	// idea: in-order DFS traverse. O(h) Time (h = height of BST)
    int count = 0;
    int result = Integer.MIN_VALUE;
    
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }
    
    public void traverse(TreeNode root, int k) {
        if (root == null || count == k) return;
        traverse(root.left, k);
        count++;	// count increases when traverse finishes, i.e. from the smallest leaf
        if (count == k) {
        	result = root.val;
        	return;
        }
        if (count < k) {
        	traverse(root.right, k);
        }     
    }
}