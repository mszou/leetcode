/**
 * Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.
 * Note:
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 * Example 1:
 * Input:
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 * Output: 
 * 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 * Input:
 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * Output: 
 * 2
 * Explanation:
 * a-bcd- 
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 * Input:
 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * Output: 
 * 1
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 * The character '-' signifies an empty space on the screen.
 */

public class Solution {
	public int wordTyping(String[] sentence, int rows, int cols) {
		// idea: Join the words into a sentence string and use a pointer to analyze the start pos
		// of each row. The ans is the final start index divided by the length of sentence string.
		String s = String.join(" ", sentence) + " ";	// join words into a sentence, including space
		int start = 0, len = s.length();
		for (int i = 0; i < rows; i++) {
			start += cols;
			if (s.charAt(start % len) == ' ') {	// start % len is the index in non-repeated string
				start++;	// skip leading ' ' in the line
			} else {
				while (start > 0 && s.charAt((start - 1) % len) != ' ') {
					start--;
				}
			}
		}
		return start / len;
	}
}