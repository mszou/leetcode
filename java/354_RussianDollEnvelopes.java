/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // idea: DP + Binary Search. (Similar to Longest Increasing Subsequence). O(nlogn) Time
        // 1. First sort the array, ascend on width and descend on height if width are same
        // 2. Then find the longest increasing subsequence based on height
        // Note: when width are same, envelope with greater height cannot contain a shorter one
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
        	return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>(){
        	public int compare(int[] arr1, int[] arr2){
        		if (arr1[0] == arr2[0]) {
        			return arr2[1] - arr1[1];	// descend on height if width are same
        		} else {
        			return arr1[0] - arr2[0];	// ascend on width
        		}
        	}
        });
        // find the LIS based on height
        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] e : envelopes) {
        	int index = Arrays.binarySearch(dp, 0, len, e[1]);
        	if (index < 0) {
        		index = -(index + 1);
        	}
        	dp[index] = e[1];
        	if (index == len) {
        		len++;
        	}
        }
        return len;
    }
}