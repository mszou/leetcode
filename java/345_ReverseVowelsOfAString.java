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
        // idea: use two pointers from left & right, find vowel pairs and swap them until pointers meet
        // use a HashSet<Character> storing all vowels to reduce the look up time to O(1)
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
            // find a pair of vowels from left & right for swapping, skip consonants
        	if (!vowels.contains(chars[left])) {
        		left++;
        		continue;
        	}
        	if (!vowels.contains(chars[right])) {
        		right--;
        		continue;
        	}
        	char temp = chars[left];
        	chars[left] = chars[right];
        	chars[right] = temp;
        	left++;
        	right--;
        }
        return new String(chars);
    }
}