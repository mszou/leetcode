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
    // naive O(n^2) idea: compute the prefix sums S[i] = S(0,i), then S(i,j) = S[j] - S[i-1]
    public int countRangeSum(int[] nums, int lower, int upper) {
    	int n = nums.length;
        long[] sums = new long[n + 1];  // sum[i] is the sum of first i nums
        for (int i = 0; i < n; ++i) {
        	sums[i + 1] = sums[i] + nums[i];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
        	for (int j = i + 1; j <= n; ++j) {
        		if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper) {
        			ans++;
        		}
        	}
        }
        return ans;
    }

    // // sol 2: merge sort. O(nlogn) Time. (another sol Balanced BST is also O(nlogn) Time)
    // // count[i] stores # eligible ranges start with nums[i], then final ans = sum of count.
    // // see https://discuss.leetcode.com/topic/33738/share-my-solution
    // public int countRangeSum(int[] nums, int lower, int upper) {
    //     int n = nums.length;
    //     long[] sums = new long[n + 1];  // sum[i] is the sum of first i nums
    //     for (int i = 0; i < n; ++i) {
    //         sums[i + 1] = sums[i] + nums[i];
    //     }
    //     return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    // }

    // // start & end are unreachable
    // private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
    // 	if (end - start <= 1) {
    // 		return 0;
    // 	}
    // 	int mid = start + (end - start) / 2;
    // 	int count = countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);
    // 	int j = mid, k = mid, t = mid;
    // 	long[] cache = new long[end - start];
    // 	for (int i = start, r = 0; i < mid; ++i, ++r) {
    // 		while (k < end && sums[k] - sums[i] < lower) {
    // 			k++;
    // 		}
    // 		while (j < end && sums[j] - sums[i] <= upper) {
    // 			j++;
    // 		}
    // 		while (t < end && sums[t] < sums[i]) {
    // 			cache[r++] = sums[t++];
    // 		}
    // 		cache[r] = sums[i];
    // 		count += j - k;
    // 	}
    // 	System.arraycopy(cache, 0, sums, start, t - start);
    // 	return count;
    // }

    int count = 0;
    int lower;
    int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        long[] temp = new long[nums.length + 1];
        sum[0] = 0;
        this.lower = lower;
        this.upper = upper;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (long) nums[i - 1];
        }
        
        mergesort(sum, 0, sum.length - 1, temp);
        return count;
    }
    
    private void mergesort(long[] sum, int start, int end, long[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(sum, start, mid, temp);
        mergesort(sum, mid + 1, end, temp);
        merge(sum, start, mid, end, temp);
    }
    
    private void merge(long[] sum, int start, int mid, int end, long[] temp) {
        int right = mid + 1;
        int index = start;
        int low = mid + 1, high = mid + 1;
        for (int left = start; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) {
                low++;
            }
            while (high <= end && sum[high] - sum[left] <= upper) {
                high++;
            }
            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
            count += high - low;
        }
        while (right <= end) {
            temp[index++] = sum[right++];
        }
        
        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
    }
    
}