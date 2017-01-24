/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 * Follow up:
 * Could you do it using only constant space complexity?
 */

public class Solution {
	public boolean verifyPreorder(int[] preorder) {
		// sol 1: simulate the traversal, use stack to store the nodes of which we're still
		// use a varible to track the lower bound for next node.	O(n) Time, O(logn) Space.
		//我们先设一个最小值low，然后遍历数组，如果当前值小于这个最小值low，返回false，对于根节点，我们将其压入栈中，然后往后遍历，
		//如果遇到的数字比栈顶元素小，说明是其左子树的点，继续压入栈中，直到遇到的数字比栈顶元素大，那么就是右边的值了，我们需要找到
		//是哪个节点的右子树，所以我们更新low值并删掉栈顶元素，然后继续和下一个栈顶元素比较，如果还是大于，则继续更新low值和删掉栈顶，
		//直到栈为空或者当前栈顶元素大于当前值停止，压入当前值，这样如果遍历完整个数组之前都没有返回false的话，最后返回true即可
		int low = Integer.MIN_VALUE;
		Stack<Integer> path = new Stack<>();
		for (int p : preorder) {
			if (p < low) {
				return false;
			}
			while (!path.empty() && p > path.peek()) {
				low = path.pop();
			}
			path.push(p);
		}
		return true;

		// sol 2: same as sol 1, reduce to O(1) Space by using the given array instead of stack.
		int low = Integer.MIN_VALUE, i = -1;
		for (int p : preorder) {
			if (p < low) {
				return false;
			}
			while (i >= 0 && p > preorder[i]) {
				low = preorder[i--];
			}
			preorder[++i] = p;
		}
		return true;
	}
}