/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // idea: DP + Binary Search. (Similar to Longest Increasing Subsequence). O(nlogn) Time
        // 1. First sort the array, ascend on width and descend on height when width are same
        // 2. Then find the longest increasing subsequence based on height
        // Note: when width are same, envelope with greater height cannot contain a shorter one
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
        	return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>(){
        	public int compare(int[] a, int[] b){
        		if (a[0] == b[0]) {
        			return b[1] - a[1];	// descend on height if width are same
        		} else {
        			return a[0] - b[0];	// ascend on width
        		}
        	}
        });
        // find the Longest Increasing Subsequence based on height
        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] e : envelopes) {
        	int index = Arrays.binarySearch(dp, 0, len, e[1]); // search e[1] in dp[0,len)
        	if (index < 0) {   // not exist, (-(insertion point) - 1)
        		index = -(index + 1); // get the insertion pos
        	}
        	dp[index] = e[1];  // actually do nothing if e[1] exists
        	if (index == len) {    // is the newly largest
        		len++;
        	}
        }
        return len;
    }
}