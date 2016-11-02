/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * Note:
 * Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
 * Examples:
 * Input:
 * nums = [1,2,3,4,5]
 * m = 2
 * Output: 9
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5],
 * where the largest sum among the two subarrays is only 9.
 */

public class Solution {
    public int splitArray(int[] nums, int m) {
        // idea: Binary Search
        // 1. The answer is between maximum value of input array numbers and sum of those numbers.
        // 2. Use binary search to approach the correct answer. We have l = max number of array; r = sum of all numbers in the array; Every time we do mid = (l + r) / 2;
        // 3. Use greedy to narrow down left and right boundaries in binary search.
        //  3.1 Cut the array from left to form subarrays.
        //  3.2 Try best to make sure that the sum of each subarray is large enough but still less than mid.
        //  3.3 We'll end up with two results: the array can divided into no more than m subarrays or cannot.
        //  If it can't, it means that the mid value we pick is too small because we've already tried our best to make sure each part holds as many non-negative numbers as we can but still have numbers left. So it's impossible to cut the array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to mid = l + 1;
        //  If it can, it is either we successfully divide the array into m parts and the sum of each part is less than mid, or we used up all numbers before we reach m. Both of them mean that we should lower mid because we need to find the minimum one. This leads to r = mid - 1;
        long[] preSum = new long[nums.length + 1];
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) {
            return (int)sum;
        }
        // binary search, use mid to find the optimal valid split method: sum of subarray <= mid
        long l = max; long r = sum;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)l;
    }
    
    // check whether nums can be divided into m subarrays whose sum are all no larger than target
    public boolean valid(long target, int[] nums, int m) {
        int count = 1;  // # of subarrays needed
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {   // if sum exceeds target, then start a new subarray
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}