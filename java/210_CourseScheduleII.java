/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * Hints:
 * This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 */

public class Solution {
	// idea: BFS. start with a node with 0 indegree, schedule this course and reduce 1 to the indegrees of all courses that
	// have it as prerequiste. repeat this process until no node with 0 indegree, see whether we scheduled all the courses.
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] indegree = new int[numCourses];
		List<List<Integer>> adjs = new ArrayList<>();	// Adjacency List for the graph
		// initialize graph
		for (int i = 0; i < numCourses; i++) {
			adjs.add(new ArrayList<Integer>());
		}
		for (int[] edge : prerequisites) {
			indegree[edge[0]]++;
			adjs.get(edge[1]).add(edge[0]);
		}
		// solve by BFS
		int[] res = new int[numCourses];
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		int count = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			res[count++] = curr;
			for (int next : adjs.get(curr)) {
				if (--indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		if (count == numCourses) {
			return res;
		} else {
			return new int[0];
		}
	}
}