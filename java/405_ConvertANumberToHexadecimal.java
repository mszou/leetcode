/**
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 * Note:
 * All letters in hexadecimal (a-f) must be in lowercase.
 * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * You must not use any method provided by the library which converts/formats the number to hex directly.
 * Example 1:
 * Input:
 * 26
 * Output:
 * "1a"
 * Example 2:
 * Input:
 * -1
 * Output:
 * "ffffffff"
 */

public class Solution {
	// idea: convert 4 bits at a fime from right to left and create a mapping
	char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    public String toHex(int num) {
    	if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
		while (num != 0) {
			int temp = (num & 0b1111);	// get the final 4 bits
			sb.append(map[temp]);
			num >>>= 4;	// right shift 4 bits (unsigned)
		}
		return sb.reverse().toString();
    }
}