//	Implement a simple regex parser which, given a string and a pattern, returns a booleanindicating whether the input matches the pattern. 
//	By simple, we mean that the regex can only contain special character: * (star), . (dot), + (plus). The star means what youd expect, 
//	that there will be zero or more of previous character in that place in the pattern. The dot means any character for that 
//	position. The plus means one or more of previous character in that place in the pattern.

// idea: interval DP, dp[i][j] is the result for matching input.substring(0,i) with regex.substring(0,j)
public class RegexMatch {
	public boolean isMatch(String input, String regex) {
		if (regex == null || regex.length() == 0) {
			return false;
		}
		int wordLen = input.length();
		int regexLen = regex.length();
		boolean[][] dp = new boolean[wordLen + 1][regexLen + 1];
		dp[0][0] = true;
		for (int i = 0; i <= wordLen; i++) {
			for (int j = 1; j <= regexLen; j++) {
				char c = regex.charAt(j - 1);
				if (c == '*') {
					dp[i][j] = (j > 1 && dp[i][j - 2]) || (i > 0 && dp[i - 1][j] && match(input.charAt(i - 1), regex.charAt(j - 2)));
				} else if (c == '+') {
					dp[i][j] = i > 0 && j > 1 && match(input.charAt(i - 1), regex.charAt(j - 2)) && (dp[i - 1][j - 2] || dp[i - 1][j]);
				} else {
					dp[i][j] = i > 0 && dp[i - 1][j - 1] && (match(input.charAt(i - 1), c));
				} 
			}
		}
		return dp[wordLen][regexLen];
	}

	private boolean match(char ichar, char rchar) {
		return ichar == rchar || rchar == '.';
	}

	public static void main(String[] args) {
		RegexMatch s = new RegexMatch();
		System.out.println(s.isMatch("aabc", "a+b*."));
	}

}
