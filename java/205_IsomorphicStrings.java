/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Note:
 * You may assume both s and t have the same length.
 */

public class Solution {
	public boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		// sol 1: naive, use 2 HashMap to store the two directions of mapping
		HashMap<Character, Character> sToT = new HashMap<Character, Character>();
		HashMap<Character, Character> tToS = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			char cs = s.charAt(i);
			char ct = t.charAt(i);
			if (!sToT.containsKey(cs)) {
				if (tToS.containsKey(ct)) {
					return false;
				} else {
					sToT.put(cs, ct);
					tToS.put(ct, cs);
				}
			} else if (!tToS.containsKey(ct)) {
				return false;
			} else {
				if (sToT.get(cs) != ct || tToS.get(ct) != cs) {
					return false;
				}
			}
		}
		return true;

		// sol 2: utilize the ASCII code for characters as their unique index
		int[] m1 = new int[256];	// 256 is the size for ASCII characters
		int[] m2 = new int[256];
		for (int i = 0; i < s.length(); i++) {
			int s_idx = (int)s.charAt(i);
			int t_idx = (int)t.charAt(i);
			if (m1[s_idx] != m2[t_idx]) {
				return false;
			}
			m1[s_idx] = i + 1;	// record the position of last occurrance
			m2[t_idx] = i + 1;
		}
		return true;
	}
}