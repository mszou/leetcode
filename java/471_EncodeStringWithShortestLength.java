/** 
 * Given a non-empty string, encode the string such that its encoded length is the shortest.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note:
 * k will be a positive integer and encoded string will not be empty or have extra space.
 * You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 * Example 1:
 * Input: "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 * Input: "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 * Input: "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 * Example 4:
 * Input: "aabcaabcd"
 * Output: "2[aabc]d"
 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 * Example 5:
 * Input: "abbbabbbcabbbabbbc"
 * Output: "2[2[abbb]c]"
 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 */

public class Solution {
	// idea: interval DP. dp[i][j] = s.substring(i, j+1) (index i to j) in encoded form, the formula is:
	// 1. dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) for all possible k in range [i,j), choose the min length
	// 2. or the string itself has some pattern in it which could be repeated to make ir shorter.
	// O(n^3) Time.
	public String encode(String s) {
		String[][] dp = new String[s.length()][s.length()];
		for (int l = 0; l < s.length(); l++) {	// l is the length of substring to be encoded
			for (int i = 0; i < s.length() - l; i++) {	// i is the start of substring
				int j = i + l;	// j is the end of substring
				String substr = s.substring(i, j + 1);
				dp[i][j] = substr;
				// if string length < 5, encoding will not shorten the length.
				if (j - i < 4) {
					continue;
				}
				// Loop for trying all results that we get after dividing the strings into 2 and combine the results of 2 substrings
				for (int k = i; k < j; k++) {
					if((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()){
						dp[i][j] = dp[i][k] + dp[k + 1][j];
					}
				}
				// Loop for checking if string can itself found some pattern in it which could be repeated.
				for (int k = 0; k < substr.length(); k++) {
					String repeatStr = substr.substring(0, k + 1);
					if (repeatStr != null && substr.length() % repeatStr.length() == 0 && substr.replaceAll(repeatStr, "").length() == 0) {
						String ss = substr.length() / repeatStr.length() + "[" + dp[i][i + k] + "]";
						if (ss.length() < dp[i][j].length()) {
							dp[i][j] = ss;
						}
					}
				}
			}
		}
		return dp[0][s.length() - 1];
	}
}