/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
 * Return:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
 */

public class Solution {
	public List<List<String>> groupStrings(String[] strings) {
		// idea: use the string starting with 'a' in the each shifting sequence as the key,
		// then each sequence has a unique key and strings in the same sequence have same key.
		// use a HashMap<key, word list> to store, sort the list before adding to the result.
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strings) {
			String key = findKey(str);
			if (!map.containsKey(key)) {
				List<String> list = new ArrayList<String>();
				map.put(key, list);
			}
			map.get(key).add(str);
		}
		for (String key : map.keySet()) {
			List<String> list = map.get(key);
			Collections.sort(list);
			res.add(list);
		}
		return res;
	}

	// returns the key (starting with 'a') for the giving String s
	private String findKey(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		int offset = s.charAt(0) - 'a';
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			sb.append((char)(c - offset));
		}
		return sb.toString();
	}
}