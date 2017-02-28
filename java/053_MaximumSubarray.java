/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // sol 1: Greedy algorithm. Traverse the array and compute accumulative sum, reset it when
        // it becomes negative. Keep track of the maximal sum of subarray.   O(n) Time, O(1) Space.
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);	// record the maximal sum of subarray so far
            sum = Math.max(sum, 0);	// if accumulative sum < 0, discard all num before this
        }
        return max;


        // // sol 2: DP. O(n) Time, O(n) Space.
        // int n = nums.length;
        // int[] global = new int[n];	// global[i] is the max sum of subarray so far (before nums[i])
        // int[] local = new int[n];	// local[i] is the max sum of subarray containing nums[i]
        // global[0] = nums[0];
        // local[0] = nums[0];
        // for (int i = 1; i < n; i++) {  
        //     local[i] = Math.max(nums[i], local[i - 1] + nums[i]);  
        //     global[i] = Math.max(local[i], global[i - 1]);  
        // }  
        // return global[n - 1];


        // // sol 3: Prefix Sum. O(n) Time. subtract the min prefix sum from the accumulative sum
        // int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     sum += nums[i];
        //     max = Math.max(max, sum - minSum);
        //     minSum = Math.min(minSum, sum);
        // }
        // return max;


        // // sol 4: divide & conquer. O(nlogn) Time.
        // // Step1. Find the middle element of the array. max subarray may contain that element or not.
        // // Step2.1 If not contain the middle, then apply the same algorithm to the two halves separately.
        // // Step2.2 If contains, then res = max suffix subarray of the left + max prefix subarray of the right half
        // // Step3 return the maximum of those three answers.
        // return helper(nums, 0, nums.length - 1);
    }

    // // helper function for sol 4 (divide and conquer)
    // private int helper(int[] nums, int start, int end) {
    // 	if (start == end) {
    // 		return nums[start];
    // 	}
    // 	int mid = (start + end) / 2;
    // 	int leftAns = helper(nums, start, mid);
    // 	int rightAns = helper(nums, mid + 1, end);
    // 	int leftMaxSuffix = nums[mid];
    // 	int rightMaxPrefix = nums[mid + 1];
    // 	int temp = 0;
    // 	for (int i = mid; i >= start; i--) {
    // 		temp += nums[i];
    // 		if (temp > leftMaxSuffix) {
    // 			leftMaxSuffix = temp;
    // 		}
    // 	}
    // 	temp = 0;
    // 	for (int i = mid + 1; i <= end; i++) {
    // 		temp += nums[i];
    // 		if (temp > rightMaxPrefix) {
    // 			rightMaxPrefix = temp;
    // 		}
    // 	}
    // 	return Math.max(Math.max(leftAns, rightAns), leftMaxSuffix + rightMaxPrefix);
    // }
}