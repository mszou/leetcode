/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 * A solution set is: 
 * [
 *   [7],
 *   [2, 2, 3]
 * ]
 */

public class Solution {
	// idea: backtracking, first sort, then for each candidate, consider adding it then remove and backtracking
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();	// list of all combinations
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<Integer>();	// record a combination
        Arrays.sort(candidates);
        helper(candidates, target, path, 0, res);
        return res;
    }
    
    // looking for a combination with current path and other nums starting from candidates[index]
    private void helper(int[] candidates, int target, List<Integer> path, int index, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        int prev = -1;   // No need to have this if no duplicates in the candidates
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (prev != -1 && prev == candidates[i]) {   // used to skip duplicates
                continue;
            }
            path.add(candidates[i]);
            helper(candidates, target - candidates[i], path, i, result);	// here index is still i because chosen number can be used unlimited times
            path.remove(path.size() - 1);	// back-tracking
            prev = candidates[i];
        }
    }
}