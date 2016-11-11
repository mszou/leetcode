/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * A solution set is: 
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 */

public class Solution {
    // idea: backtracking, first sort, then for each candidate, consider adding it then remove and backtracking
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<Integer>();
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
        int prev = -1;   //No need if no duplicates in the candidates
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (prev != -1 && prev == candidates[i]) {   // used to skip duplicates
                continue;
            }
            path.add(candidates[i]);
            helper(candidates, target - candidates[i], path, i + 1, result);    // here index becomes i+1 because each num can be used only once
            path.remove(path.size() - 1);   // backtracking
            prev = candidates[i];
        }
    }
}