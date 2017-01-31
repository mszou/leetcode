/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 * Example 1:
 *      0          3
 *      |          |
 *      1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * Example 2:
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class Solution {
	// idea: Union Find. initialize every node in the set only containing itself. Then for each
	// edge, check whether the two nodes belong to different sets, if so, union these two sets.
	// optimization: path compression can reduce time by 50%. The complexity for M quick union +
	// path compression on N objects is O(N + MlogN). Here M = 2E, N = V, so O(V+2ElogV) Time.
	public int countComponents(int n, int[][] edges) {
		int[] roots = new int[n];	// initialize: n sets
		for (int i = 0; i < n; i++) {
			roots[i] = i;
		}
		for (int[] e : edges) {
			int root1 = find(roots, e[0]);
			int root2 = find(roots, e[1]);
			if (root1 != root2) {
				roots[root1] = root2;	// union this two sets
				n--;	// the number of components decreased by 1 due to union
			}
		}
		return n;
	}

	private int find(int[] roots, int id) {	// find the set that id belongs to
		while (roots[id] != id) {
			roots[id] = roots[roots[id]];	// optional: path compression
			id = roots[id];
		}
		return id;
	}
}