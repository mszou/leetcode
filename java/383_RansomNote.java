/**
 * Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
 * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */

public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // // sol 1: use Map<char, times>
        // HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        // for (char m : magazine.toCharArray()) {
        // 	if (map.containsKey(m)) {
        // 		map.put(m, map.get(m) + 1);
        // 	} else {
        // 		map.put(m, 1);
        // 	}
        // }
        // for (char r : ransomNote.toCharArray()) {
        // 	int newCount = map.getOrDefault(r, 0) - 1;
        // 	if (newCount < 0) {
        // 		return false;
        // 	} else {
        // 		map.put(r, newCount);
        // 	}
        // }
        // return true;

        // sol 2: use array. arr[i] records the times of i-th letter occurs in magazine
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
        	arr[magazine.charAt(i) - 'a']++;
        }
        for (int j = 0; j < ransomNote.length(); j++) {
        	if (--arr[ransomNote.charAt(j) - 'a'] < 0) {
        		return false;
        	}
        }
        return true;
    }
}