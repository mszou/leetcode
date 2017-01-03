/**
 * Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom note  can  be  constructed  from  the  magazines;  otherwise,  it  will  return  false.   
 * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */

public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // // sol 1: use Map<character, available times>
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
        // 	if (newCount < 0) {    // this letter is not available
        // 		return false;
        // 	} else {
        // 		map.put(r, newCount);
        // 	}
        // }
        // return true;

        // sol 2: use array. count[i] records the times of i-th letter occurs in magazine
        int[] count = new int[26];
        for (char m : magazine.toCharArray()) {
        	count[m - 'a']++;
        }
        for (char r : ransomNote.toCharArray()) {
        	if (--count[r - 'a'] < 0) {
        		return false;
        	}
        }
        return true;
    }
}