/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */

// extension for LC72(Edit Distance): insert/delete/replace a character is 1 step (edit distance)
public class Solution {
	// sol 1: 3 cases: 1.length diff > 1, return false; 2. length diff = 1, then check whether
	// we can get a string same with shorter one after removing a char from the longer string;
	// 3. length diff = 0, check whether they have only one difference in corresponding positions.
	public boolean isOneEditDistance(String s, String t) {
		int len1 = s.length();
		int len2 = t.length();
		if (Math.abs(len1 - len2) > 1) {
			return false;
		} else if (len1 > len2) {	// len1 - len2 = 1
			for (int i = 0; i < len2; i++) {
				if (s.charAt(i) != t.charAt(i)) {
					return s.substring(i + 1).equals(t.substring(i));
				}
			}
		} else if (len1 < len2) {
			for (int i = 0; i < len1; i++) {
				if (s.charAt(i) != t.charAt(i)) {
					return t.substring(i + 1).equals(s.substring(i));
				}
			}
		} else {	// len1 == len2
			boolean diff = false;
			for (int i = 0; i < len1; i++) {
				if (s.charAt(i) != t.charAt(i)) {
					if (diff) {
						return false;
					}
					diff = true;
				}
			}
			return true;
		}
	}

	// sol 2: compare corresponding positions in two strings. when encounter a difference, check the
	// lengths of s & t: if same, check if all following chars are same; if different, check if the
	// rest substring in longer one is same to substring starting from current char in shorter one.
	// if no different chars in the loop, only possibility is deleting the end char in the longer one,
	// so check if the length difference is 1.	O(min{m,n}) Time, O(1) Space.
	public boolean isOneEditDistance(String s, String t) {
		int len1 = s.length();
		int len2 = t.length();
		if (Math.abs(len1 - len2) > 1) {
			return false;
		for (int i = 0; i < Math.min(len1, len2); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (len1 == len2) {
					return s.substring(i + 1).equals(t.substring(i + 1));
				} else if (len1 < len2) {
					return s.substring(i).equals(t.substring(i + 1));
				} else {
					return t.substring(i).equals(s.substring(i + 1));
				}
			}
		}
		return len1 != len2;	// Math.abs(s.length() - t.length()) == 1;
	}
}