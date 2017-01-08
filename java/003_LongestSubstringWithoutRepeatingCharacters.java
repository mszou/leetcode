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
        // idea: substring in the sliding window bounded by 2 pointers (left & i),
        // use hashset / hashmap to check uniqueness. O(n) Time, O(n) Space.
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLen = 0;    // maximal length so far
        int left = 0;	// point to the head of current substring
        // sol 1: HashSet
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            // keep removing the character from left until no duplicate
            if (set.contains(s.charAt(i))) {
                while (left < i && s.charAt(left) != s.charAt(i)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            } else {
                set.add(s.charAt(i));
                maxLen = Math.max(maxLen, i - left + 1);
            }
        }
        return maxLen;
        
        // sol 2: HashMap<char, index>
        // HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        // for (int i = 0; i < s.length(); i++) {
        //     // remove the left part and update left if s.charAt(i) hits
        //     if (map.containsKey(s.charAt(i))) {
        //         while (left < map.get(s.charAt(i))) {
        //             map.remove(s.charAt(left++));
        //         }
        //         map.remove(s.charAt(left++));
        //     } 
        //     map.put(s.charAt(i), i);
        //     maxLen = Math.max(maxLen, i - left + 1);
        // }
        // return maxLen;
    }
}