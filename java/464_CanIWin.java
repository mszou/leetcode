/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 * What if we change the game so that players cannot re-use integers?
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * Example
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * Output:
 * false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */

public class Solution {
	// idea: use an array to indicate the state of each number, 0 means available, 1 means it has already
	// been chosen before. In the situation of current states, we check the winnability of choosing each
	// available number. Use map to memorize the winnability for visited states to avoid duplicate work.
	// trick: transfer the int[] state into a String as the key of map.
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if (desiredTotal <= 0) {
			return true;
		}
		if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
			return false; 	// 1+2+...+max < desiredTotal, not reachable
		}
		return canIWin(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
	}

	private boolean canIWin(int total, int[] state, HashMap<String, Boolean> map) {
		String currState = Arrays.toString(state);
		if (map.containsKey(currState)) {
			return map.get(currState);
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i] == 0) {
				state[i] = 1;	// I choose nums[i], i.e. i+1
				if (i + 1 >= total || !canIWin(total - (i + 1), state, map)) {	// I win or the other player cannot win
					map.put(currState, true);
					state[i] = 0;
					return true;
				}
				state[i] = 0;	// not choose nums[i], backtracking
			}
		}
		map.put(currState, false);
		return false;
	}
}