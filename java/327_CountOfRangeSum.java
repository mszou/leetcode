/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */

public class Solution {
	/**
	 * @param nums: A given integer array
	 * @param lower: The given lower bound for range sum
	 * @param upper: The given upper bound for range sum
	 * @return the number of range sums that lie in the given [lower, upper] range.
	 * Note: Range sum S(i,j) = nums[i] + nums[i+1] + ... + nums[j]
	 */
    public int countRangeSum(int[] nums, int lower, int upper) {
    	int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i) {
        	sums[i + 1] = sums[i] + nums[i];
        }

        // // naive O(n^2) idea: compute the prefix sums S[i] = S(0,i), then S(i,j) = S[j] - S[i-1]
        // int ans = 0;
        // for (int i = 0; i < n; ++i) {
        // 	for (int j = i + 1; j <= n; ++j) {
        // 		if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper) {
        // 			ans++;
        // 		}
        // 	}
        // }
        // return ans;

        // sol 2: merge sort. O(nlogn) Time. (another sol Balanced BST is also O(nlogn) Time)
        // count[i] = count of a <= S[j] - S[i] <= b with j > i; ans = sum(count[:])
        // see https://discuss.leetcode.com/topic/33738/share-my-solution
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
    	if (end - start <= 1) {
    		return 0;
    	}
    	int mid = (start + end) / 2;
    	int count = countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);
    	int j = mid, k = mid, t = mid;
    	long[] cache = new long[end - start];
    	for (int i = start, r = 0; i < mid; ++i, ++r) {
    		while (k < end && sums[k] - sums[i] < lower) {
    			k++;
    		}
    		while (j < end && sums[j] - sums[i] <= upper) {
    			j++;
    		}
    		while (t < end && sums[t] < sums[i]) {
    			cache[r++] = sums[t++];
    		}
    		cache[r] = sums[i];
    		count += j - k;
    	}
    	System.arraycopy(cache, 0, sums, start, t - start);
    	return count;
    }
}