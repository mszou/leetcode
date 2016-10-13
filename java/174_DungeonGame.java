/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * 
 * -2(K)	-3 		3
 * 	-5		-10		1
 * 	10		30	  -5(P)
 *
 *
 */

public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        // idea: DP. hp[i][j] is the minimum HP needed at position (i, j) to reach P
        // compute from bottom-right to top-left。 Note: always need at least 1 hp to keep alive, so use max to avoid hp <= 1
        // update rule: hp[i][j] = Math.max(1, Math.min(hp[i+1][j], hp[i][j+1]) - dungeon[i][j])
        int M = dungeon.length;
        int N = dungeon[0].length;
        int[][] hp = new int[M][N];
        // initialize bottom-right corner, the last row (only consider right) and last column (only consider below)
        hp[M - 1][N - 1] = Math.max(1, 1 - dungeon[M - 1][N - 1]);
        for (int j = N - 2; j >= 0; j--) {
        	hp[M - 1][j] = Math.max(1, hp[M - 1][j + 1] - dungeon[M - 1][j]);
        }
        for (int i = M - 2; i >= 0; i--) {
        	hp[i][N - 1] = Math.max(1, hp[i + 1][N - 1] - dungeon[i][N - 1]);
        }
        // compute from bottom-right to top-left
        for (int i = M - 2; i >= 0; i--) {
        	for (int j = N - 2; j >= 0; j--) {
    			hp[i][j] = Math.max(1, Math.min(hp[i+1][j], hp[i][j+1]) - dungeon[i][j]);
        	}
        }
        return hp[0][0];
    }
}