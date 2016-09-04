/**
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. 
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // idea: use 2 pointers (leftBound & i) to limit the substring + hashset/hashmap to achieve uniqueness
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;    // maximal length so far
        int leftBound = 0;	// point to the head of current substring
        // sol 1: HashSet
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            // remove the left part and update leftBound if s.charAt(i) hits
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound++;
                }
                leftBound++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }
        return max;
        
        // sol 2: HashMap<char, index>
        // HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        // for (int i = 0; i < s.length(); i++) {
        //     // remove the left part and update leftBound if s.charAt(i) hits
        //     if (map.containsKey(s.charAt(i))) {
        //         while (leftBound < map.get(s.charAt(i))) {
        //             map.remove(s.charAt(leftBound++));
        //         }
        //         map.remove(s.charAt(leftBound++));
        //     } 
        //     map.put(s.charAt(i), i);
        //     max = Math.max(max, i - leftBound + 1);
        // }
        // return max;
    }
}