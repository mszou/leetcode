//	find all the combinations of a string in lowercase and uppercase. For example, string "ab" can be   
//	decoded as "ab", "Ab", "aB", "AB". So you will have 2^n (n = number of chars in the string) output
//	strings. The goal is to test each of these string and see if it match a hidden string.
//	Given a method decode(testEncStr) which will return the decoded int id if testEncStr is decodeable or will 
//	throw an exception (or return null) if not, implement a new method decodeFind(String badEncStr) which takes 
//	a string and returns the decoded int id. http://buttercola.blogspot.com/2015/11/airbnb-decode-string.html

import java.util.*;
// use the binary expression of 0 ~ 2^n-1 to represent the way of decode, 
public class DecodeFind {
	public Integer decodeFind(String badEncString) {
		if (badEncString == null || badEncString.length() == 0) {
			return null;
		}
		int size = badEncString.length();
		int total = (int)Math.pow(2, size);	// because pow() returns double
		for (int i = 0; i < total; i++) {
			int index = i;
			StringBuilder sb = new StringBuilder(badEncString);
			for (int j = 0; j < size; j++) {
				char c = badEncString.charAt(j);
				if ((index & 1) == 1) {	// change lower-case c to upper-case
					if (c >= 'a' && c <= 'z') {
						sb.setCharAt(j, (char) (c + 'A' - 'a'));
					}
				} else {	// change upper-case c to lower-case
					if (c >= 'A' && c <= 'Z') {
						sb.setCharAt(j, (char) (c + 'a' - 'A'));
					}
				}
				index = index >> 1;
			}
			Integer res = decode(sb.toString());
			if (res != null) {
				return res;
			}
		}
		return null;
	}

	private Integer decode(String testEncStr) {
		String truth = "kljJJ324hijkS_";
        if (testEncStr.equals(truth)) {
            return 848662;
        } else {
            return null;
        }
	}

	public static void main(String[] args) {
		DecodeFind s = new DecodeFind();
        System.out.println(s.decodeFind("kljjJ324hIjkS_"));
	}

}
