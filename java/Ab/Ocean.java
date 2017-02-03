/** Water to Ocean
* Given: An array of strings where L indicates land and W indicates water,
*   and a coordinate marking a starting point in the middle of the ocean.
*
* Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
*   An ocean coordinate is defined to be the initial coordinate if a W, and
*   any coordinate directly adjacent to any other ocean coordinate.
*
* void findOcean(String[] map, int row, int column);
*
* String[] map = new String[]{
*  "WWWLLLW",
*  "WWLLLWW",
*  "WLLLLWW"
* };
* printMap(map);
*
* STDOUT:
* WWWLLLW
* WWLLLWW
* WLLLLWW
*
* findOcean(map, 0, 1);
*
* printMap(map);
*
* STDOUT:
* OOOLLLW
* OOLLLWW
* OLLLLWW
*/

import java.util.*;
// idea: BFS, use a queue to store the cells of water we should change next
// O(mn) Time, O(mn) Space.
public class Ocean {
	public void findOcean(String[] map, int row, int col) {
		if (map == null || map.length == 0) {
			return;
		}
		int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
		int rowSize = map.length;
		int colSize = map[0].length();
		char[][] newMap = new char[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				newMap[i][j] = map[i].charAt(j);
			}
		}
		boolean[][] visited = new boolean[rowSize][colSize];
		Queue<Integer> q = new LinkedList<Integer>();
		int index = row * colSize + col;
		q.offer(index);
		visited[row][col] = true;
		while (!q.isEmpty()) {
			int out = q.poll();
			int i = out / colSize;
			int j = out % colSize;
			newMap[i][j] = 'O';
			for (int[] dir : directions) {
				int x = i + dir[0];
				int y = j + dir[1];
				if (x < 0 || x >= rowSize || y < 0 || y >= colSize || visited[x][y] || newMap[x][y] != 'W') {
					continue;
				}
				q.offer(x * colSize + y);
				visited[x][y] = true;
			}
		}
		for (int i = 0; i < rowSize; i++) {
			map[i] = String.valueOf(newMap[i]);
		}
	}

	public static void main(String[] args) {
		Ocean s = new Ocean();
		String[] map = {"WWWWLLLW","WWLLWWWW","WLLWLWWW"};
        s.findOcean(map, 1, 6);
        for(String str : map) {
            System.out.println(str);
        }
	}

}
