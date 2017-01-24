/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 */

public class Solution {
    public String reverseWords(String s) {
        // idea: split the string by spaces, append words from right to left. O(n) Time, O(n) Space.
        if (s == null || s.length() == 0) {
        	return "";
        }
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
        	if (!words[i].equals("")) {
        		sb.append(words[i]).append(" ");
        	}
        }
        if (sb.length() == 0) {
        	return "";
        } else {
        	return sb.toString().substring(0, sb.length() - 1);	// remove the last space " "
        }

        // sol 2: use stack (store words) + StringBuilder. O(n) Time, O(n) Space.
    }
}