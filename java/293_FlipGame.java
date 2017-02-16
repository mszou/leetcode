/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 * For example, given s = "++++", after one move, it may become one of the following states:
 * [
 *   "--++",
 *   "+--+",
 *   "++--"
 * ]
 * If there is no valid move, return an empty list [].
 */

public class Solution {
	public List<String> generatePossibleNextMoves(String s) {
		// idea: traverse to find all "++" and replace them with "--", O(n) Time.
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder(s);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {	// or use s.substring(i-1,i+1).equals("++")
				sb.replace(i - 1, i + 1, "--");	// flip "++" into "--"
				res.add(sb.toString());
				sb.replace(i - 1, i + 1, "++");	// recover to the origin string
			}
		}
		return res;
	}
}