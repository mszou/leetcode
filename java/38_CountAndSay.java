/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */

public class Solution {
    public String countAndSay(int n) {
    	// idea: iterately count the previous string, use StringBuilder for each round
        String oldString = "1";
        while (n > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < oldString.length(); i++) {  // count and say
            	int count = 1;
            	while ((i + 1) < oldString.length() && oldString.charAt(i) == oldString.charAt(i + 1)) {
            		count++;
            		i++;
            	}
            	sb.append(count).append(oldString.charAt(i));
            }
            oldString = sb.toString();
            n--;
        }
        return oldString;
    }
}