/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 */

public class Solution {
	/**
	 * @param version1 a String
	 * @param version2 another String
	 * @return 1 (if version1 > version2), -1 (if version1 < version2), or 0 (otherwise)
	 */
    public int compareVersion(String version1, String version2) {
    	// idea: compare versions from higher level to lower level, until they are different
    	// function for Integer a.compareTo(b) returns 1 (a > b), -1 (a < b), or 0 (a = b)
        // first split the strings by level
        String[] level1 = version1.split("\\.");	// escape character of dot
        String[] level2 = version2.split("\\.");
        
        int len = Math.max(level1.length, level2.length);
        for (int i = 0; i < len; i++) {
            Integer v1 = i < level1.length ? Integer.parseInt(level1[i]) : 0;
            Integer v2 = i < level2.length ? Integer.parseInt(level2[i]) : 0;
            int compare = v1.compareTo(v2); //a.compareTo(b) returns 0 (a=b), 1 (a>b), or -1 (a<b)
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}