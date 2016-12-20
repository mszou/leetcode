/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 *      3
 *     / \
 *    2   3
 *     \   \ 
 *      3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *      3
 *     / \
 *    4   5
 *   / \   \ 
 *  1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
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
	// sol 1: naive, recursive, compare sum of level i + level i - 2 and sum of level i - 1
    public int rob(TreeNode root) {
        if (root == null) {
        	return 0;
        }
        int val = 0;
        if (root.left != null) {
        	val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
        	val += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }

    // sol 2: optimized, use a hashmap to store the results already got for visited subtrees
    Map<TreeNode, Integer> map;

    public int rob(TreeNode root) {
		map = new HashMap<TreeNode, Integer>();
    	return helper(root, map);
    }

    private int helper(TreeNode root, Map<TreeNode, Integer> map) {
    	if (root == null) {
    		return 0;
    	}
    	if (map.containsKey(root)) {
    		return map.get(root);
    	}
    	int val = 0;
    	if (root.left != null) {
    		val += helper(root.left.left, map) + helper(root.left.right, map);
    	}
    	if (root.right != null) {
    		val += helper(root.right.left, map) + helper(root.right.right, map);
    	}
    	val = Math.max(val + root.val, helper(root.left, map) + helper(root.right, map));
    	map.put(root, val);
    	return val;
    }

    // sol 3: There are only 2 situations for each node: being robbed or not. Let a function return
    // two results, respectively are the max can get if this node is not robbed and if it is robbed
    public int rob(TreeNode root) {
    	int[] res = helper(root);
    	return Math.max(res[0], res[1]);
    }

    private int[] helper(TreeNode root) {
    	int[] res = new int[2];
    	if (root == null) {
    		return res;
    	}
    	int[] left = helper(root.left);
    	int[] right = helper(root.right);
    	// not rob root, then root.left and root.right can either be robbed or not
    	res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    	// rob root, then root.left and root.right cannot be robbed
    	res[1] = root.val + left[0] + right[0];
    	return res;
    }	    

}