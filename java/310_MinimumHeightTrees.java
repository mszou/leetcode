/**
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example 1:
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 * return [1]
 * Example 2:
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 * return [3, 4]
 * Hint:
 * How many MHTs can a graph have at most?
 * Note:
 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 * (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */

public class Solution {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		// idea: BFS, Similar to Topological Sort. Start from "leaves", remove all the nodes with 1 degree,
		// then after the removal, there will be other nodes become new leaves, remove thm in the next round.
		// repeat this process until there are only 1 or 2 nodes left, which is the result we want. The root
		// of MHT is the middle point of the longest leaf-to-leaf path in the graph.	O(n) Time, O(n) Space.		
		List<Integer> res = new ArrayList<>();	// at most two result
		if (n == 1 && edges.length == 0) {  // corner case: single node
			res.add(0);	// only has a 0 in the tree
			return res;
		}
		if (n == 0 || edges == null || edges.length != n - 1 || edges[0].length != 2) {
			return res;
		}
		List<Set<Integer>> adj = new ArrayList<>(n);	// adjacent list
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<>());
		}
		for (int[] e : edges) {		// add undirected edges, i.e. two directions
			adj.get(e[0]).add(e[1]);
			adj.get(e[1]).add(e[0]);
		}
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (adj.get(i).size() == 1) {	// add all leaves
				leaves.add(i);
			}
		}
		while (n > 2) {
			n -= leaves.size();		// remember to subtract # leaves from n
			List<Integer> newLeaves = new ArrayList<>();
			for (int leaf : leaves) {
				int next = adj.get(leaf).iterator().next();	// leaf has only one connection
				adj.get(next).remove(leaf);
				if (adj.get(next).size() == 1) {	// next is a new leaf
					newLeaves.add(next);
				}
			}
			leaves = newLeaves;
		}	// end with n = 1 or n = 2, and all result(s) in 'leaves'
		res = leaves;
		return res;
	}
}
