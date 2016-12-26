/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * Hints:
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 */

public class Solution {
	// idea: this problem is equivalent to detecting a cycle in the graph. an edge from u to v means u is prerequisite of v.
	// Both BFS(better) and DFS can be used to solve it using the idea of "topological sort".

	// sol 1: BFS. start with a node with 0 indegree, schedule this course and reduce 1 to the indegrees of all courses that
	// have it as prerequiste. Repeat this process until no more 0-indegree nodes, see whether we scheduled all the courses.
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] graph = new int[numCourses][numCourses];	// Adjacency Matrix for the graph
		int[] indegree = new int[numCourses];	// indegree[i] stores # prerequisites for course i
		for (int i = 0; i < prerequisites.length; i++) {
			int curr = prerequisites[i][0];
			int pre = prerequisites[i][1];
			if (graph[pre][curr] == 0) {
				graph[pre][curr] = 1;
				indegree[curr]++;
			}
		}
		int count = 0;	// # courses that we have scheduled so far
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);	// use queue to store nodes with 0 indegree
			}
		}
		while (!queue.isEmpty()) {
			int course = queue.poll();	// schedule this course
			count++;
			for (int i = 0; i < numCourses; i++) {
				if (graph[course][i] != 0) {	// this course is prerequisite of i
					if (--indegree[i] == 0) {
						queue.offer(i);	// i is eligible to schedule next
					}
				}
			}
		}
		return count == numCourses;
	}

	// sol 2: DFS, starts at every node in the graph and traverse all the nodes to see
	// if we'll encounter a node that has already been visited (means there is a cycle)
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];	// Adjacency List for the graph
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			int curr = prerequisites[i][0];
			int pre = prerequisites[i][1];
			graph[pre].add(curr);
		}
		for (int i = 1; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}
		return true;
	}

	private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course]) {
			return false;
		} else {
			visited[course] = true;
		}
		for (int i = 0; i < graph[course].size(); i++) {
			if (!dfs(graph, visited, (int)graph[course].get(i))) {
				return false;
			}
		}
		visited[course] = false;
		return true;
	}
}