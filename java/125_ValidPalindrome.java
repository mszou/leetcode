/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */

public class Solution {
    public boolean isPalindrome(String s) {
    	// idea: use two pointers from head and tail, compare pairs of characters until they meet
        // use Character.isLetterOrDigit(c) to check if c is an alphanumeric character
        // use Character.toLowerCase(c) to convert c to lower case before comparing
        if (s == null || s.length() <= 1) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        char cHead, cTail;
        while (left < right) {
            cHead = s.charAt(left);
            cTail = s.charAt(right);
            // only consider alphanumeric characters
            if (!Character.isLetterOrDigit(cHead)) {
                left++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                right--;
            } else {
            	// case-insensitive: convert characters to lower case before comparing
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}