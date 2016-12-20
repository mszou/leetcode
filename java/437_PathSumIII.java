/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * Return 3. The paths that sum to 8 are:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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
	// sol 1: recursive, DFS and look at all possible paths
	// O(nlogn) Time for balanced tree, O(n^2) for the worst case (just a straight line)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
        	return 0;
        }
        return countPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int countPath(TreeNode node, int sum) {	// count # valid paths starting from this node
    	if (node == null) {
    		return 0;
    	}
    	int res = 0;
    	if (sum == node.val) {
    		res++;
    	}
    	res += countPath(node.left, sum - node.val);
    	res += countPath(node.right, sum - node.val);
    	return res;
    }

    // sol 2: optimized, the sum for a path starting from a non-root node can be expressed as the difference between the sum
    // of a path starting from the root and the prefix sum to that non-root node. use HashMap to store <prefix sum, frequency>
    // pairs before getting to the current node. Then each time check (runningSum - target) in the map
    // Each recursion returns the total # valid paths ending with the current node or in the subtrees rooted at this node.
    public int pathSum(TreeNode root, int sum) {
    	HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
    	preSum.put(0, 1);
    	return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode curr, int runningSum, int target, HashMap<Integer, Integer> preSum) {
    	if (curr == null) {
    		return 0;
    	}
    	runningSum += curr.val;
    	int numPathToCurr = preSum.getOrDefault(runningSum - target, 0);	// # valid paths ending with the current node
    	preSum.put(runningSum, preSum.getOrDefault(runningSum, 0) + 1);
    	int res = numPathToCurr + helper(curr.left, runningSum, target, preSum) + helper(curr.right, runningSum, target, preSum);
    	preSum.put(runningSum, preSum.get(runningSum) - 1);	// remove current node from runningSum
    	return res;
    }
}