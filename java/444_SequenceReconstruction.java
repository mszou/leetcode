/** 
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 * Example 1:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 * Output:
 * false
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 * Example 2:
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 * Output:
 * false
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 * Output:
 * true
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * Output:
 * true
 */

public class Solution {
	public boolean sequenceReconstruction(int[] org, int[][] seqs) {
		// idea: BFS Topological sort. The sequence can be uniquely reconstructed iff. there is only 1 node
		// with 0 indegree at any time during recunstruction until no node left.	O(n) Time, O(n) Space.
		Map<Integer, Set<Integer>> map = new HashMap<>();	// store <num i, nums after i>
		Map<Integer, Integer> indegree = new HashMap<>();	// store indegree of each num, can also use array
		for (int[] seq : seqs) {
			if (seq.length == 1) {	// a single number sequence
				if (!map.containsKey(seq[0])) {
					map.put(seq[0], new HashSet<>());
					indegree.put(seq[0], 0);
				}
			} else {	// put every adjacent pair into map
				for (int i = 0; i < seq.length - 1; i++) {
					if (!map.containsKey(seq[i])) {
						map.put(seq[i], new HashSet<>());
						indegree.put(seq[i], 0);
					}
					if (!map.containsKey(seq[i + 1])) {
						map.put(seq[i + 1], new HashSet<>());
						indegree.put(seq[i + 1], 0);
					}
					if (map.get(seq[i]).add(seq[i + 1])) {
						indegree.put(seq[i + 1], indegree.get(seq[i + 1]) + 1);
					}
				}
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0) {
				q.offer(entry.getKey());	// put node with 0 indegree into the queue
			}
		}
		int index = 0;	// index in the reconstructed sequence & original sequence
		while (!q.isEmpty()) {
			if (q.size() > 1) {
				return false;
			}
			int curr = q.poll();
			if (index == org.length || curr != org[index++]) {
				return false;	// not match the original sequence
			}
			for (int next : map.get(curr)) {
				indegree.put(next, indegree.get(next) - 1);
				if (indegree.get(next) == 0) {
					q.offer(next);
				}
			}
		}
		return index == org.length && index == map.size();	// use up all numbers
	}
}