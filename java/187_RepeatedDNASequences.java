/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 */

public class Solution {
	public List<String> findRepeatedDnaSequences(String s) {
		// idea: use two hashset (seen & repeated). O(n) Time, Space:(n-10)*10 character in the mem
		// improvement: can do some encoding on the substrings to reduce the space complexity
		if (s == null || s.length() < 10) {
			return new ArrayList<String>();
		}
		HashSet<String> seen = new HashSet<String>();
		HashSet<String> repeated = new HashSet<String>();
		for (int i = 0; i < s.length() - 9; i++) {
			String str = s.substring(i, i + 10);
			if (!seen.add(str)) {	// already exist
				repeated.add(str);
			}
		}
		return new ArrayList<String>(repeated);
	}
}