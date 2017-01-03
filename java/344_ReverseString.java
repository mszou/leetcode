/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh".
 */

public class Solution {
    public String reverseString(String s) {
    	if (s == null || s.length() <= 1) {
        	return s;
        }

        // // sol 1: use StringBuilder and its reverse method (4ms)
        // StringBuilder sb = new StringBuilder(s);
        // return sb.reverse().toString();

        // sol 2: use two pointers from left and right, swap each pair until they meet (3ms)
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
        	char temp = chars[left];
        	chars[left] = chars[right];
        	chars[right] = temp;
        	left++;
        	right--;
        }
        return new String(chars);

        // // sol 3: use stack (Time Limit Exceeded)
        // Stack<Character> stack = new Stack<Character>();
        // for (int i = 0; i < s.length(); i++) {
        // 	stack.push(s.charAt(i));
        // }
        // String res = "";
        // for (int i = 0; i < s.length(); i++) {
        // 	res = res + stack.pop();
        // }
        // return res;
    }
}