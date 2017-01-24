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
	// sol 1: "Binary Search", count the nodes in left subtree and compare with k, locate
    // the kthSmallest in which subtree according to the comparison.
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (count >= k) {
            return kthSmallest(root.left, k);
        } else if (count < k - 1) {
            return kthSmallest(root.right, k - 1 - count);
        } else {
            return root.val;
        }
    }

    private int countNodes(TreeNode n) {
        if (n == null) {
            return 0;
        }
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    // sol 2: DFS recursive. inorder traversal, for any (sub)tree, add # nodes in left subtree, then 1
    // for root, if count still < k, then traverse the right subtree. O(h) Time (h = height of BST, logn)
    int count = 0;
    int result = Integer.MIN_VALUE;
    
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }
    
    private void traverse(TreeNode root, int k) {
        if (root == null || count == k) {
            return;
        }
        traverse(root.left, k);
        count++;	// count increases 1 for root when left subtree traversal finishes
        if (count == k) {
        	result = root.val;
        	return;
        }
        if (count < k) {
        	traverse(root.right, k);
        }     
    }

    // sol 3: DFS iterative. count the nodes while doing inorder traversal using a stack.
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (k != 0) {
            TreeNode curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            TreeNode right = curr.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;  // never reach here as long as k is valid
    }
}