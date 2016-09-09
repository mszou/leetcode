/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Given s = "hello", return "holle".
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * Note:
 * The vowels does not include the letter "y".
 */

public class Solution {
    public String reverseVowels(String s) {
        // idea: two pointers from left & right, find a pair of vowels and swap them
        // use a HashSet<Character> to reduce the look up time to O(1)
        if (s == null || s.length() <= 1) {
        	return s;
        }
        HashSet<Character> vowels = new HashSet<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
        	if (!vowels.contains(chars[left])) {	// find the left vowel
        		left++;
        		continue;
        	}
        	if (!vowels.contains(chars[right])) {	// find the right vowel
        		right--;
        		continue;
        	}
        	char c = chars[left];
        	chars[left] = chars[right];
        	chars[right] = c;
        	left++;
        	right--;
        }
        return new String(chars);
    }
}