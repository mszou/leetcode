/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). 
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 * Hint:
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */

public class Solution {
	// idea: strobogrammatic numbers of length 1 can only be 0,1,8, when length >= 2, it can be
	// constructed by a strobogrammatic number of length n - 2 and a pair at beginning and end.
	// The pair can be chosen from (0,0),(1,1),(6,9),(8,8),(9,9). Remember to avoid leading 0.
	public List<String> findStrobogrammatic(int n) {
		return construct(n, n);
	}

	List<String> construct(int contructLength, int totalLength) {
		if (contructLength <= 0) {
			return new ArrayList<String>(Arrays.asList(""));
		}
		if (contructLength == 1) {
			return new ArrayList<String>(Arrays.asList("0", "1", "8"));
		}
		List<String> list = construct(contructLength - 2, totalLength);
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if (contructLength != totalLength) {	// avoid leading 0
				res.add("0" + s + "0");
			}
			res.add("1" + s + "1");
			res.add("6" + s + "9");
			res.add("8" + s + "8");
			res.add("9" + s + "6");
		}
		return res;
	}
}