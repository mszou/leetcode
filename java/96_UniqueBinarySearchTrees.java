/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *   2     1         2                 3
 */

public class Solution {
    public int numTrees(int n) {
    	// idea: DP. nums[i] is # unique BST's for i numbers
        if (n == 0) {
            return 1;
        }
        int[] nums = new int[n + 1];
        nums[0] = nums[1] = 1;
        for (int i = 2; i <= n; i++) {
        	// j is the root, then j-1 numbers in the leftTree, i-j numbers in the rightTree
            for (int j = 1; j <= i; j++) {
                nums[i] += nums[j-1] * nums[i-j];
            }
        }
        return nums[n];
    }
}