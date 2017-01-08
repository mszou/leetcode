/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of 
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 */

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // idea: Sliding Window of length K, use two hashmaps to record the standard and current words & times
        // when analyzing, use two pointers l & r, move the pointers K places (word length) at a time
        // ** ask interviewer if words is empty, should I return empty list
        List<Integer> res = new ArrayList<>();
        if (words.length == 0 || s.length() < words.length * words[0].length()) {
            return res;
        }   
        int N = s.length(), M = words.length, K = words[0].length();
        Map<String, Integer> map = new HashMap<>();	// the desired words and times
        Map<String, Integer> curMap = new HashMap<>();
        for (String str : words) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }                     
        }
        String str = null, tmp = null;
        // all words are of the same length K, so the analysis will be periodic
        // i is the very beginning index to analyze, it's unnecessary to consider i >= K
        for (int i = 0; i < K; i++) {
            int count = 0;  // reset count 
            for (int l = i, r = i; r + K <= N; r += K) {	// two pointers l & r
            	// analyze next word: substring(r, r + K)
                str = s.substring(r, r + K);
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str)) {
                        curMap.put(str, curMap.get(str) + 1);
                    } else {
                        curMap.put(str, 1);
                    }                          
                    if (curMap.get(str) <= map.get(str)) {
                        count++;	// count this word
                    }
                    while (curMap.get(str) > map.get(str)) {	// str is redundant
                    	// remove words from left
                        tmp = s.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;	// move the left pointer
                        if (curMap.get(tmp) < map.get(tmp)) {
                        	count--;
                        }
                    }
                    if (count == M) {	// got one concatenation
                        res.add(l);
                        tmp = s.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                } else {	// reset, and move the starting pointer
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();	// reset
        }
        return res;
    }
}