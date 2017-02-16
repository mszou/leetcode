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
	// sol 1: use stack, more specificly, utilize the depth of stack. Simulate the
	// preorder traversal on the string, a valid serialization should end up with
	// stack depth == 0 and pointer == nodes.length - 1 (the index of last leaf).
	private int stackSize;	// increases when we push nodes, decreases when pop

	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		int i = findLeaf(nodes, 0);
		while (stackSize > 0) {
			stackSize--;
			i = findLeaf(nodes, ++i);
		}
		return i == nodes.length - 1;
	}

	// push nodes into stack, from index i to next leaf
	private int findLeaf(String[] nodes, int i) {
		while (i < nodes.length && !nodes[i].equals("#")) {
			stackSize++;
			i++;
		}
		return i;
	}
	
	// sol 2.1 & 2.2: utilize the stucture of binary tree	
	public boolean isValidSerialization(String preorder) {
		// property 1: every non-leaf node has 2 outdegree and 1 indegree (except root)
		// and every leaf node has 0 outdegree and 1 indegree, keep tracking the diff.
		String[] nodes = preorder.split(",");
		int diff = 1;	// outdegree - indegree, initialized as 1 (for the compensation of root indegree)
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
		// the second condition is aimed to avoid the situation of several trees
		while (i < nodes.length && nonleaves + 1 != leaves) {
			if (nodes[i].equals("#")) {
				leaves++;
			} else {
				nonleaves++;
			}
			i++;
		}
		return nonleaves + 1 == leaves && i == nodes.length;
	}

	// sol 3: recursive
	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		return valid(nodes, 0) == nodes.length - 1;
	}

	// checks validness and returns the index of last node in the current subtree.
	// checking from bottom-up manner and bubble up failure condition.
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