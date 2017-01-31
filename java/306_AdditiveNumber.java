/**
 * Additive number is a string whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * Follow up:
 * How would you handle overflow for very large input integers?
 */

public class Solution {
	// idea: generate the first two numbers and check the rest of the string matches the sum recursively.
	// if the length of first two numbers are i & j, then i in [1,n/2] and length of their sum >= max(i,j)
	// sol 1: recursive. try every possible 1st & 2nd number and check whether can form additive sequence
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) {
			return false;
		}
		int n = num.length();
		// num[0, i-1] is the first number, num[i, i+j-1] is the second number
		for (int i = 1; i <= n / 2; i++) {
			if (num.charAt(0) == '0' && i > 1) {
				return false;	// has a leading zero
			}
			Long n1 = Long.parseLong(num.substring(0, i));
			for (int j = 1; Math.max(i, j) <= n - i - j ; j++) {	// the length of third num should >= max(i,j)
				if (num.charAt(i) == '0' && j > 1) {
					break;	// has a leading zero
				}
				Long n2 = Long.parseLong(num.substring(i, i + j));
				if (canFormAdditiveSequence(n1, n2, i + j, num)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canFormAdditiveSequence(Long n1, Long n2, int start, String num) {
		if (start == num.length()) {	// reach the end
			return true;
		}
		n2 = n2 + n1;
		n1 = n2 - n1;
		String n3 = n2.toString();
		return num.startsWith(n3, start) && canFormAdditiveSequence(n1, n2, start + n3.length(), num);
	}

	// sol 2: iterative.
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) {
			return false;
		}
		int n = num.length();
		for (int i = 1; i <= n / 2; i++) {
			for (int j = 1; Math.max(i, j) <= n - i - j ; j++) {
				if (canForm(i, j, num)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canForm(int i, int j, String num) {
		if (num.charAt(0) == '0' && i > 1 || num.charAt(i) == '0' && j > 1) {
			return false;
		}
		Long n1 = Long.parseLong(num.substring(0, i));
		Long n2 = Long.parseLong(num.substring(i, i + j));
		String n3;
		for (int start = i + j; start < num.length(); start += n3.length()) {
			n2 = n2 + n1;
			n1 = n2 - n1;
			n3 = n2.toString();
			if (!num.startsWith(n3, start)) {
				return false;
			}
		}
		return true;
	}
}