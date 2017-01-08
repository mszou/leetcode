/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class Solution {
    public String minWindow(String s, String t) {
        // idea: use 2 pointers (to define a window) and hashmap (to record characters and times)
        // 1. 2 pointers start from the beginning; 2. Move the right pointer to find a valid window;
        // 3. When a valid window is found, move the left pointer to shrink the size of the window.
        if (s == null || s.length() < t.length() || s.length() == 0) {
            return "";
        }
        // put target characters and times into the hashmap
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int left = 0, right;	// two pointers indicate the range of window
        int minLeft = 0;	// the position of left pointer for the minimal window so far
        int minLen = s.length() + 1;	// record the length of minimal window so far
        int count = 0;	// record the number of target characters found in the current substring
        for (right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
                if (map.get(s.charAt(right)) >= 0) {
                    count++;
                }
            }
            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                if (map.containsKey(s.charAt(left))) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) > 0) {
                        count--;
                    }
                }
                left++;
            }
        }
        // if no such substring
        if (minLen > s.length()) {
            return "";
        }
        return s.substring(minLeft, minLeft + minLen);
    }
}