/**
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }
        // sol 1: compare needle with each substring of same length in haystack
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (needle.equals(haystack.substring(i, i + needle.length()))) {
                return i;
            }
        }
        return -1;

		// // sol 2: use two pointers, compare the substring starting from i with needle (check charAt(j))
		// for (int i = 0; i <= haystack.length() - needle.length(); i++) {
		// 	for (int j = 0; ; j++) { // compare needle.charAt(j) & haystack.charAt(i + j)
		// 		if (j == needle.length()) return i;
		// 		if (needle.charAt(j) != haystack.charAt(i + j)) break;
		// 	}
		// }
		// return -1;
    }
}