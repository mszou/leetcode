/**
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // idea: backtracking
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        // no need to sort, if required, Arrays.sort(nums);
        List<Integer> list = new ArrayList<Integer>();
        helper(res, list, nums);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
    	if (list.size() == nums.length) {	// all nums have been used
    		res.add(new ArrayList<Integer>(list));
    		return;
    	}
    	for (int i = 0; i < nums.length; i++) {
    		if (list.contains(nums[i])) {	// this num has already been used
    			continue;
    		}
    		list.add(nums[i]);
    		helper(res, list, nums);
    		list.remove(list.size() - 1);	// backtracking, remove the last added num
    	}
    	return;
    }
}