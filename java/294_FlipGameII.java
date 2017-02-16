/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to determine if the starting player can guarantee a win.
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */

public class Solution {
	// idea: DFS. the player can guarantee a win when the other player cannot win after this flip.
	// Recursively check the winnability for a string. use Map to memorize win & lose for strings.
	// For a string of length n, there are at most n - 1 ways to replace "++" to "--", so the Time
	// T(N) = (N-1) * T(N-2) = (N-1) * (N-3) * T(N-4) ... = (N-1) * (N-3) * (N-5) * ... ~ O(N!!)
	public boolean canWin(String s) {
		if (s == null || s.length() < 2) {
			return false;
		}
		Map<String, Boolean> map = new HashMap<>();
		return canWin(s, map);
	}

	private boolean canWin(String s, Map<String, Boolean> map) {
		if (map.containsKey(s)) {	// already checked s
			return map.get(s);
		}
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {	//	s[i-1, i] is "++"
				String flip = s.substring(0, i - 1) + "--" + s.substring(i + 1);	// flip
				if (!canWin(flip, map)) {	// the other player cannot win on the flipped string
					map.put(s, true);
					return true;
				}
			}
		}
		map.put(s, false);	// cannot find a solution to guarantee a win on current string s
		return false;
	}
}