/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are NOT zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers = {2, 7, 11, 15}, target = 9
 * Output: index1 = 1, index2 = 2
 */

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
    	// idea: two pointers from left and right, compare sum & target and shrink the scope.
        // add 1 to the indices in result to get the 1-based indices.   O(n) Time.
        if (numbers == null || numbers.length < 2) {
        	return null;
        }
        int left = 0, right = numbers.length - 1;
        while (left < right) {
        	if (numbers[left] + numbers[right] == target) {
        		break;
        	} else if (numbers[left] + numbers[right] < target) {
        		left++;
        	} else {
        		right--;
        	}
        }
        if (left < right) {	// found one solution
        	return new int[]{left + 1, right + 1};	// return the 1-based indices
        } else {	// no solution
        	return new int[]{-1, -1};
        }
    }
}