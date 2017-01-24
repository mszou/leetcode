/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

public class WordDistance {
	// idea: use HashMap<word, indices> to store all the indices for each word, then for
	// the given two words, traverse their indices and find the shortest distance.
	// Time: O(n) for intialization, O(k1+k2) for query, k1,k2 are freq of word1, word2. O(n) Space.
	private Map<String, List<Integer>> map;

	public WordDistance(String[] words) {	// initialize the words array
		map = new HashMap<String, List<Integer>>();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (map.containsKey(word)) {
				map.get(word).add(i);
			} else {
				List<Integer> indices = new ArrayList<Integer>();
				indices.add(i);
				map.put(word, indices);
			}
		}
	}

	public int shortest(String word1, String word2) {
		// assume that word1 does not equal to word2, and word1 and word2 are both in the list.
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int res = Integer.MAX_VALUE;
		for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
			int idx1 = list1.get(i);
			int idx2 = list2.get(j);
			if (idx1 < idx2) {
				res = Math.min(res, idx2 - idx1);
				i++;
			} else {
				res = Math.min(res, idx1 - idx2);
				j++;
			}
		}
		return res;
	}
}