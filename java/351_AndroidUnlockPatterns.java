/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * Rules for a valid pattern:
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6 
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * Example:
 * Given m = 1, n = 1, return 9.
 */

public class Solution {
	// idea: DFS + backtracking. 1,3,7,9 are symmetric, so we can just calculate the result starting from 1
	// and multiply by 4 to get result for 1,3,7,9. Similarly, 2,4,6,8 are symmetric, so calculate the result
	// starting from 2 and multiply by 4. Add them up along with result starting from 5 to get total results.
	public int numberOfPatterns(int m, int n) {
		boolean[] visited = new boolean[10];
		int[][] passes = new int[10][10];	// line i-j passes through passes[i][j]
		passes[1][3] = passes[3][1] = 2;	// 1-2-3 & 3-2-1
		passes[1][7] = passes[7][1] = 4;	// 1-4-7 & 7-4-1
		passes[3][9] = passes[9][3] = 6;	// 3-6-9 & 9-6-3
		passes[7][9] = passes[9][7] = 8;	// 7-8-9 & 9-8-7
		passes[1][9] = passes[9][1] = passes[2][8] = passes[8][2] = passes[3][7] = passes[7][3] = passes[4][6] = passes[6][4] = 5;
		int res = 0;
		for (int i = m; i <= n; i++) {	// DFS search each length from m to n
			res += dfs(visited, passes, 1, i - 1) * 4;	// start from 1,3,7,9
			res += dfs(visited, passes, 2, i - 1) * 4;	// start from 2,4,6,8
			res += dfs(visited, passes, 5, i - 1);		// start from 5
		}
		return res;
	}

	private int dfs(boolean[] visited, int[][] passes, int pos, int steps) {
		if (steps < 0 || visited[pos]) {
			return 0;
		}
		if (steps == 0) {
			return 1;
		}
		visited[pos] = true;
		int res = 0;
		for (int i = 1; i <= 9; i++) {
			// i not visited and i is adjacent to current pos or the number passed has already been visited
			if (!visited[i] && (passes[pos][i] == 0 || visited[passes[pos][i]])) {
				res += dfs(visited, passes, i, steps - 1);
			}
		}
		visited[pos] = false;	// backtracking, not choose this pos
		return res;
	}
}