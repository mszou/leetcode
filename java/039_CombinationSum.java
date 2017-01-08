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
	// idea: dfs + back-tracking, first sort, then for each candidate, first add it then remove and backtracking
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        List<Integer> comb = new ArrayList<Integer>();	// record one combination
        Arrays.sort(candidates);
        helper(candidates, target, comb, 0, res);
        return res;
    }
    
    // looking for a combination with current comb and other nums starting from candidates[index]
    private void helper(int[] candidates, int target, List<Integer> comb, int index, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(comb));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1]) {   // skip duplicates
                continue;   // no need to have this if no duplicates in the candidates
            }
            comb.add(candidates[i]);
            helper(candidates, target - candidates[i], comb, i, res);	// here index is still i because chosen number can be used unlimited times
            comb.remove(comb.size() - 1);	// back-tracking
        }
    }
}