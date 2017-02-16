/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * For example,
 * Given the following words in dictionary,
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * The correct order is: "wertf".
 * Note:
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */

public class Solution {
	// idea: Topological sort, BFS, using a Queue to store letters with 0 indegree.
	// compare adjacent words, the first different letter reflect the lexicographical order.
	public String alienOrder(String[] words) {
		String res = "";
		if (words == null || words.length == 0) {
			return res;
		}
		Map<Character, Set<Character>> map = new HashMap<>();
		Map<Character, Integer> indegree = new HashMap<>();
		for (String word : words) {
			for (char c : word.toCharArray()) {
				indegree.put(c, 0);	// initialize every letter with indegree 0
			}
		}
		for (int i = 0; i < words.length - 1; i++) {	// compare adjacent words
			String curr = words[i];
			String next = words[i + 1];
			int len = Math.min(curr.length(), next.length());
			for (int j = 0; j < len; j++) {	// compare adjacent words digit by digit
				char c1 = curr.charAt(j);
				char c2 = next.charAt(j);
				if (c1 != c2) {	// c1, c2 different and c1 comes before c2 in the dictionary
					Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
					if (!set.contains(c2)) {
						set.add(c2);
						map.put(c1, set);
						indegree.put(c2, indegree.get(c2) + 1);
					}
					break;
				}
			}
		}
		Queue<Character> q = new LinkedList<Character>();	// stores letters with 0 indegree
		for (char c : indegree.keySet()) {
			if (indegree.get(c) == 0) {
				q.offer(c);
			}
		}
		while (!q.isEmpty()) {
			char c = q.poll();
			res += c;
			if (map.containsKey(c)) {
				for (char next : map.get(c)) {
					indegree.put(next, indegree.get(next) - 1);
					if (indegree.get(next) == 0) {
						q.offer(next);
					}
				}
			}
		}
		if (res.length() != indegree.size()) {	// cannot arrange all letters
			return "";
		}
		return res;
	}
}