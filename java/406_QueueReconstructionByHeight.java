/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 * Note:
 * The number of people is less than 1,100.
 * Example
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

public class Solution {
	public int[][] reconstructQueue(int[][] people) {
		// idea: first pick up the tallest guy, (if more than one, sort them in ascending k values)
		// then insert the next tallest guy(s) into it's k-th position, repeat until all inserted.
		List<int[]> res = new LinkedList<>();
		// sort the people in descending height, and ascending k when heights are the same
		Arrays.sort(people, new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) {
					return a[1] - b[1];
				} else {
					return b[0] - a[0];
				}
			}
		});
		for (int[] p : people) {
			res.add(p[1], p);	// insert this person into k-th position
		}
		return res.toArray(new int[people.length][]);	// change list to array
	}
}