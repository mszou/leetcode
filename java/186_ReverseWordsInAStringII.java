/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Could you do it in-place without allocating extra space?
 */

public class Solution {
	public void reverseWords(char[] s) {
		// idea: First reverse the whole sentence then reverse each word, or reverse
		// each word first then reverse the whole sentence.	O(n) Time, O(1) Space.
		reverse(s, 0, s.length - 1);
		for (int left = 0, right = 0; right <= s.length; right++) {	
			if (right == s.length || s[right] == ' ') {	// left ~ right-1 is a word
				reverse(s, left, right - 1);
				left = right + 1;
			}
		}
	}

	private void reverse(char[] s, int begin, int end) {
		while (begin < end) {
			char temp = s[begin];
			s[begin] = s[end];
			s[end] = temp;
			begin++;
			end--;
		}
	}
}