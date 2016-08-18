 /**
  * Reverse digits of an integer.
  *
  * Example1: x = 123, return 321
  * Example2: x = -123, return -321
  *
  * Have you thought about this?
  * 1. If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
  * 2. Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
  *  then the reverse of 1000000003 overflows. How should you handle such cases? For the purpose
  *  of this problem, assume that your function returns 0 when the reversed integer overflows.
  */


public class Solution {
    public int reverse(int x) {
        Boolean neg = false;
        if (x < 0) {
        	neg = true;
        	x = 0 - x;
        }
        int res = 0;
        while (x > 0) {
        	int temp = x % 10;
        	res = res * 10 + temp;
        	if (res % 10 != temp) {	// overfolw
        		return 0;
        	}
        	x = x / 10;
        }
        if (neg) {
        	res = 0 - res;
        }
        return res;
    }
}