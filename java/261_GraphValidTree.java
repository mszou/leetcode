/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Hint:
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
 * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class Solution {
	public boolean validTree(int n, int[][] edges) {
		// idea: Union find. A graph with n vertices and n - 1 edges without cycle is a tree.
		// so we just need to check the number of edges and whether there is a cycle.
		// Time: Worst O(n^2) (|V|*|E|), Best O(n) (|E|). O(n) Space (|V|).
		if (n == 0 || edges.length != n - 1) {
			return false;
		}
		int[] nums = new int[n];
		Arrays.fill(nums, -1);
		// Union find
		for (int[] edge : edges) {
			int x = find(nums, edge[0]);
			int y = find(nums, edge[1]);
			// if two vertices in the same set, then has a cycle
			if (x == y) {
				return false;
			}
			nums[x] = y;	// else, union these two nodes (sets)
		}
		return true;
	}

	// find() takes at worst (go through all nodes) O(|V|) time and at best O(1) Time
	private int find(int[] nums, int i) { // find the set that nums[i] belongs to
		if (nums[i] == -1) {
			return i;
		} else {
			return find(nums, nums[i]);
		}
	}
}