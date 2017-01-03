/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 * Hint:
 * A naive implementation of the above process is trivial. Could you come up with other methods?
 * What are all the possible results?
 * How do they occur, periodically or randomly?
 * You may find this Wikipedia article ("Digital root") useful.
 */

public class Solution {
    public int addDigits(int num) {
        if (num <= 0) {
            return num;
        }

        // sol 1: naive
        while (num > 9) {
        	num = next(num);
        }
        return num;

        // sol 2: all possible results are 1~9, and all multiples of 9 will
        // end up in 9, others will end up in num % 9. O(1) Time.
        return 1 + (num - 1) % 9;
    }

    private int next(int num) {
    	int res = 0;
    	while (num > 0) {
    		res += num % 10;
    		num /= 10;
    	}
    	return res;
    }
}