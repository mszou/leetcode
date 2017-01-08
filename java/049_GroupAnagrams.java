/**
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 */

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // idea: use HashMap<keyStr, anagrams>. Anagrams have same 1st anagram in lexicographical order (as keyStr).
        // For each string, convert to char array then sort the chars to get the 1st anagram.
        // O(nklogk) Time, n is the number of strings, k is length of each string. O(n) Space.
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // Arrays.sort(strs);   // sort strs to let results in lexicographical order
        for (String s : strs) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            String keyStr = String.valueOf(chs);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<String>());
            }
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}