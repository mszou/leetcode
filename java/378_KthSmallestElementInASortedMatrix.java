/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * Example:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * return 13.
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */

// sol 1: Binary Search, count the elements no bigger than mid until reaches k. 4ms
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n-1][n-1];
        while (start < end) {
        	int mid = start + (end - start) / 2;
        	int count = 0;
        	for (int i = 0; i < n; i++) {
        		int[] curRow = matrix[i];
        		int left = 0, right = curRow.length;
        		while (left < right) {
        			int rowMid = left + (right - left) / 2;
        			if (curRow[rowMid] > mid) {
        				right = rowMid;
        			} else {
        				left = rowMid + 1;
        			}
        		}
        		count += left;	// left equals to # elements in curRow <= mid
        	}
        	if (count < k) {
        		start = mid + 1;
        	} else {
        		end = mid;
        	}
        }
        return start;
    }
}

// // sol 2: heap (PriorityQueue). 44ms
// class Number {
//     public int x, y, val;
//     public Number(int x, int y, int val) {
//         this.x = x;
//         this.y = y;
//         this.val = val;
//     }
// }
// class NumComparator implements Comparator<Number> {
//     public int compare(Number a, Number b) {
//         return a.val - b.val;
//     }
// }

// public class Solution {
//     /**
//      * @param matrix: a matrix of integers
//      * @param k: an integer
//      * @return: the kth smallest number in the matrix
//      */
//     private boolean valid(int x, int y, int[][] matrix, boolean[][] hash) {
//         if (x < matrix.length && y < matrix[x].length && !hash[x][y]) {
//             return true;
//         }
//         return false;
//     }
    
//     int dx[] = {0,1};
//     int dy[] = {1,0};
    
//     public int kthSmallest(int[][] matrix, int k) {
//         PriorityQueue<Number> heap = new PriorityQueue<Number>(k, new NumComparator());
//         boolean[][] hash = new boolean[matrix.length][matrix[0].length];
//         heap.add(new Number(0, 0, matrix[0][0]));
//         hash[0][0] = true;
        
//         for (int i = 0; i < k - 1; i ++) {
//             Number smallest = heap.poll();
//             for (int j = 0; j < 2; j++) {
//                 int nx = smallest.x + dx[j];
//                 int ny = smallest.y + dy[j];
//                 if (valid(nx, ny, matrix, hash)) {
//                     hash[nx][ny] = true;
//                     heap.add(new Number(nx, ny, matrix[nx][ny]));
//                 }
//             }
//         }
//         return heap.peek().val;
//     }
// }