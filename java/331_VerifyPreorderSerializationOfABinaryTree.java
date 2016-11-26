/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * Example 2:
 * "1,#"
 * Return false
 * Example 3:
 * "9,#,#,1"
 * Return false
 */

public class Solution {
	// sol 1: use stack, more specificly, utilize the depth of stack.
	private int stack;

	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		int i = findLeft(nodes, 0);
		while (stack > 0) {
			stack--;
			i = findLeft(nodes, ++i);
		}
		return i == nodes.length - 1;
	}

	private int findLeft(String[] nodes, int i) {
		while (i < nodes.length && !nodes[i].equals("#")) {
			stack++;
			i++;
		}
		return i;
	}
	
	// sol 2.1 & 2.2: utilize the stucture of binary tree	
	public boolean isValidSerialization(String preorder) {
		// property 1: every non-leave node has 2 outdegree and  1 indegree (except root),
		// and every leaf node has 0 outdegree and 1 in degree
		String[] nodes = preorder.split(",");
		int diff = 1;	// diff = outdegree - indegree, initialized as 1
		for (String node : nodes) {
			if (--diff < 0) {	// a new node has an indegree, so decrease diff by 1
				return false;
			}
			if (!node.equals("#")) {	// a non-leaf node provides 2 outdegrees
				diff += 2;
			}
		}
		return diff == 0;	// a valid binary tree should have diff == 0 in the end

		// property 2: # of leaves = # of nonleaves + 1
		int nonleaves = 0, leaves = 0, i = 0;
		String[] nodes = preorder.split(",");
		// the condition after "&&" is aimed for the situation of several trees
		for (; i < nodes.length && nonleaves + 1 != leaves; i++) {
			if (nodes[i].equals("#")) {
				leaves++;
			} else {
				nonleaves++;
			}
		}
		return nonleaves + 1 == leaves && i == nodes.length;
	}

	// sol 3: recursion
	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		return valid(nodes, 0) == nodes.length - 1;
	}

	// checking balance of nodes from bottom-up manner and bubble up failure condition.
	private int valid(String[] nodes, int curr) {
		if (curr >= nodes.length) {
			return -1;
		}
		if (nodes[curr].equals("#")) {
			return curr;
		}
		// left
		int next = valid(nodes, curr + 1);
		if (next == -1) {
			return -1;
		}
		// right
		next = valid(nodes, next + 1);
		return next == -1 ? -1 : next;
	}
}