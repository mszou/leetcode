/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example, 
 * Given s = "Hello World",
 * return 5.
 */

public class Solution {
    public int lengthOfLastWord(String s) {
    	// idea: count from the end, until it meets the first empty space or reaches the beginning
        // sol 1: lower level implementation
        int length = 0;
        if (s == null || s.length() == 0) {
            return length;
        }
        // when string is long, using toCharArray() then read char[] is better than s.charAt()
        char[] chars = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (length == 0) {
                if (chars[i] == ' ') {	// consider if string end with ' '
                    continue;
                } else {
                    length++;
                }
            } else {
                if (chars[i] == ' ') {
                    break;
                } else {
                    length++;
                }
            }
        }
        return length;

        // // sol 2: more concise implementation with standard library functions
        // // just find the last index of empty space except at the end
        // s = s.trim();
        // return s.length() - s.lastIndexOf(" ") - 1;
    }
}