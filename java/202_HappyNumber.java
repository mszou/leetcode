/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, 
 * replace the number by the sum of the squares of its digits, and repeat the process until the 
 * number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */

public class Solution {
    public boolean isHappy(int n) {
    	// idea: the process either ends in 1 or loops in a cycle, so use a set to
    	// record the numbers that already occurred in the process to detect cycle
        HashSet<Integer> set = new HashSet<Integer>();
        while (n != 1) {
            if (!set.add(n)) {  // loop cycle appears
                return false;   
            }
            n = getNext(n);	// process one step
        }
        return true;
    }
    
    private int getNext(int n) {
        int sum = 0;
        // add the squares of every digit, from right to left
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}