/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * Example 1:
 * Input:
 * "tree"
 * Output:
 * "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * Input:
 * "cccaaa"
 * Output:
 * "cccaaa"
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 * Input:
 * "Aabb"
 * Output:
 * "bbAa"
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */

public class Solution {
	public String frequencySort(String s) {
		// idea: use HashMap<character, frequency> + bucket, bucket[i] stores characters with freq i.
		// counting freq, finding maxFreq, putting in buckets are all O(n), so O(n) Time, O(n) Space.
		if (s == null || s.length() <= 2) {
			return s;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {	// count the freq of each character
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		int maxFreq = 0;
		for (int freq : map.values()) {
			maxFreq = Math.max(freq, maxFreq);
		}
		// bucket[i] stores the characters whose frequency is i
		List<Character>[] bucket = new List[maxFreq + 1];
		for (char key : map.keySet()) {
			int freq = map.get(key);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(key);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = bucket.length - 1; i >= 0; i--) {
			if (bucket[i] == null) {
				continue;
			}
			for (int j = 0; j < bucket[i].size(); j++) {
				for (int k = 0; k < i; k++) {
					sb.append(bucket[i].get(j));
				}
			}
		}
		return sb.toString();
	}
}
