/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */

public class Solution {
	public boolean wordPattern(String pattern, String str) {
		// idea: build the mapping between character in the pattern and word in the string
		// Since this is a 1-to-1 mapping, we need two maps for both directions
		String[] words = str.split(" ");
		if (words.length != pattern.length()) {
			return false;
		}
		HashMap<Character, String> cToWord = new HashMap<Character, String>();
		HashMap<String, Character> wordToC = new HashMap<String, Character>();
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			if (cToWord.containsKey(c) && !cToWord.get(c).equals(words[i])) {
				return false;
			}
			if (wordToC.containsKey(words[i]) && !wordToC.get(words[i]).equals(c)) {
				return false;
			}
			cToWord.put(c, words[i]);
			wordToC.put(words[i], c);
		}
		return true;
	}
}