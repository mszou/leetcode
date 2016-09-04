/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */

public class Solution {
    public String longestCommonPrefix(String[] strs) {
    	// idea: add strings into comparison one after another, and shorten the common prefix accordingly
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];

        // // sol 1: compare digits
        // for (int i = 1; i < strs.length; i++) {
        //     int j = 0;
        //     while (j < strs[i].length() && j < pre.length() && strs[i].charAt(j) == pre.charAt(j)) {
        //         j++;
        //     }
        //     if (j == 0) {
        //         return "";
        //     }
        //     pre = pre.substring(0, j);
        // }

        // sol 2: use indexOf()
        for (int i = 1; i < strs.length; i++) {
        	while (strs[i].indexOf(pre) != 0) {	// pre is not a prefix of strs[i]
        		pre = pre.substring(0, pre.length() - 1);	// cut the last character
        	}
        }

        return pre;
    }
}