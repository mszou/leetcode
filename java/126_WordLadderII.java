/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class Solution {
	// idea: BFS + DFS + Backtracking
	// two steps: 1. Use BFS to construct a graph; 2. Use DFS (backtracking) to construct the paths from end to start.
    // three tricks: 1. Using a MAP to store the min ladder of each word, (or use a SET to store the words visited
    // in current ladder, when the current ladder was completed, delete the visited words from unvisited).
    // 2. Use Character iteration to find all possible paths.
    // 3. One word is allowed to be inserted into the queue only ONCE.
    Map<String, List<String>> map;
    List<List<String>> res;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        res = new ArrayList<List<String>>();
        if (wordList.size() == 0) {
        	return res;
        }
        int min = Integer.MAX_VALUE;
        Queue<String> queue = new ArrayDeque<String>();
        queue.add(beginWord);
        map = new HashMap<String, List<String>>();
        Map<String, Integer> ladder = new HashMap<String, Integer>();
        wordList.add(endWord);
        for (String str : wordList) {
        	ladder.put(str, Integer.MAX_VALUE);
        }
        ladder.put(beginWord, 0);

        // BFS: Dijisktra search
        while (!queue.isEmpty()) {
        	String word = queue.poll();
        	int step = ladder.get(word) + 1;	// 'step' indicates how many steps are needed to travel to one word
        	if (step > min) {
        		break;
        	}
        	for (int i = 0; i < word.length(); i++) {
        		StringBuilder sb = new StringBuilder(word);
        		for (char ch = 'a'; ch <= 'z'; ch++) {
        			sb.setCharAt(i, ch);
        			String new_word = sb.toString();
        			if (ladder.containsKey(new_word)) {
        				// check if it is the shortest path to one word
        				if (step > ladder.get(new_word)) {
        					continue;
        				} else if (step < ladder.get(new_word)) {
        					queue.add(new_word);
        					ladder.put(new_word, step);
        				} else;	// It is a KEY line. If one word already appeared in one ladder, do not insert the
        						// same word inside the queue twice, otherwise it gets TLE (Time Limit Exceeded).
        				// Build adjacent Graph
        				if (map.containsKey(new_word)) {
        					map.get(new_word).add(word);
        				} else {
        					List<String> list = new LinkedList<String>();
        					list.add(word);
        					map.put(new_word, list);
        				}
        				if (new_word.equals(endWord)) {
        					min = step;
        				}
        			}	// End: if wordList contains new_word
        		}	// End: Iteration from 'a' to 'z'
        	}	// End: Iteration from the first to the last
        }	// End while

        // Backtracking
        LinkedList<String> result = new LinkedList<String>();
        backTrace(endWord, beginWord, result);

        return res;
    }

    private void backTrace(String word, String start, List<String> list) {
    	if (word.equals(start)) {	// find a path
    		list.add(0, start);
    		res.add(new ArrayList<String>(list));
    		list.remove(0);
    		return;
    	}
    	list.add(0, word);
    	if (map.get(word) != null) {
    		for (String str : map.get(word)) {
    			backTrace(str, start, list);
    		}
    	}
    	list.remove(0);
    }
}