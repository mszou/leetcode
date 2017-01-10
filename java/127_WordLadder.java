/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class Solution {
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		// idea: Two-end BFS. the ladder can be achieved from both side. maintain two sets for beginSet and endSet.
		// try every 1-letter substitute for words the smaller set to find valid next words as update set. Increase
		// the length after each round substitution. When we get a word in the other set, then a ladder is found.
		// two-end好: smaller tree, less complexity.  O(len * wordListSize * strLen * 26) Time, O(wordListSize) Space.
		Set<String> beginSet = new HashSet<String>();
		Set<String> endSet = new HashSet<String>();
		int len = 1;
		// int strLen = beginWord.length();
		HashSet<String> visited = new HashSet<String>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {	// swap to make sure beginSet no larger than endSet
				Set<String> tempSet = beginSet;
				beginSet = endSet;
				endSet = tempSet;
			}
			Set<String> temp = new HashSet<String>();	// store the new words can be reached in this round
			for (String word : beginSet) {
				char[] chars = word.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {	// try every letter as substitute
						char old = chars[i];	// record the original character
						chars[i] = c;
						String target = String.valueOf(chars);	// looking for this "nextWord"
						if (!wordList.contains(target)) {	// not in the given wordList
							chars[i] = old;	// recover
							continue;
						}
						if (endSet.contains(target)) {	// can reach endSet
							return len + 1;	// find a ladder
						}
						if (!visited.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
						chars[i] = old;	// recover
					}
				}
			}
			beginSet = temp;	// temp is the word set after one substitute from beginSet
			len++;
		}
		return 0;
	}
}