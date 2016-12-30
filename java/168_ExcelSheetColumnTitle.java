/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */

 public class Solution {
    public String convertToTitle(int n) {
        // idea: keep dividing (n - 1) by 26

        // // recursive sol
        // String res = "";
        // if (n <= 0) {
        //     return res;
        // }
        // return convertToTitle((n - 1) / 26) + (char)((n - 1) % 26 + 'A');
        
        // non-recursive sol
        StringBuilder sb = new StringBuilder();
        if (n <= 0) {
            return sb.toString();
        }
        while (n > 0) {
            sb.append((char)((n - 1) % 26 + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}