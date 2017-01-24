/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 * For example:
 * Given s = "aabb", return ["abba", "baab"].
 * Given s = "abc", return [].
 * Hint:
 * If a palindromic permutation exists, we just need to generate the first half of the string.
 * To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
 */

public class Solution {
	public List<String> generatePalindromes(String s) {
		// idea: first count the odd characters, if there are more than one, then cannot form palindrome.
		// If there is an odd character, it must have one in the middle. Generate permutations on half of
		// the palindromic string using dfs + backtracking, and then form the full palindromic result.
		// Same characters were added in group to the list, so check the previous char to skip duplicates.
		String mid = "";
		List<String> res = new ArrayList<>();
		List<Character> list = new ArrayList<>();	// record characters in half palindromic string
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			char key = entry.getKey();
			int val = entry.getValue();
			if (val % 2 != 0) {
				if (mid == "") {
					mid += key;
				} else {
					return res;	// multiple odd characters, cannot form any palindromic string
				}
			}
			for (int i = 0; i < val / 2; i++) {
				list.add(key);
			}
		}
		generatePerm(res, list, mid, new boolean[list.size()], new StringBuilder());
		return res;
	}

	// dfs + backtracking, generate all unique permutations from the characters in list
	private void generatePerm(List<String> res, List<Character> list, String mid, boolean[] used, StringBuilder sb) {
		if (sb.length() == list.size()) {	// form a permutation
			res.add(sb.toString() + mid + sb.reverse().toString());	// form the full palindromic string
			sb.reverse();	// need to reverse sb again for recovery
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			// avoid duplicates, characters in the list are clustered since we add them in groups
			if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) {
				continue;	// for same character, add the one with smaller index first
			}
			if (!used[i]) {
				used[i] = true;
				sb.append(list.get(i));
				generatePerm(res, list, mid, used, sb);	// recursion
				// backtracking
				used[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}