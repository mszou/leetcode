/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * Return the formatted lines as:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * 
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // idea: first find the words to fit in the line, add up their length (including basic spaces in between)
        // then compute the length of slots, and the number of slots that need extra space accordingly
        // * use StringBuilder to generate strings for each line; * consider each word as " word" when computing length
        List<String> res = new LinkedList<String>();
        // fit words[i] ~ words[j-1] into a line
        for (int i = 0; i < words.length; i = j) {
            int len = -1;
            int j = i;
            while (j < words.length && len + words[j].length() + 1 > maxWidth) {
                len += words[j].length() + 1;
                j++;
            }
        	StringBuilder sb = new StringBuilder(words[i]);    // first add words[i]
        	int space = 1, extra = 0;	// length of slots, and # of slots that need extra space
        	if (j != i + 1 && j != words.length) {	// not only one word, and not last line
        		space = (maxWidth - len) / (j - i - 1) + 1;   // 1 is the basic space between words
        		extra = (maxWidth - len) % (j - i - 1);   // first several slots may have an extra space
        	}
        	for (int k = i + 1; k < j; k++) {
        		for (int s = space; s > 0; s--) {
        			sb.append(' ');
        		}
        		if (k - i <= extra) {
        			sb.append(' ');	// first several gaps needing extra space
        		}
        		sb.append(words[k]);
        	}
        	int spaceLen = maxWidth - sb.length();
        	while (spaceLen-- > 0) { // for the case only one word in the line
        		sb.append(' ');
        	}
        	res.add(sb.toString());
        }
        return res;
    }
}