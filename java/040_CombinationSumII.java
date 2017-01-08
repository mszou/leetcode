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
    // idea: back-tracking, first sort, then for each candidate, first add it then remove and backtracking
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        List<Integer> comb = new ArrayList<Integer>();
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
            comb.add(candidates[i]);
            helper(candidates, target - candidates[i], comb, i + 1, res);    // here index becomes i+1 because each num can be used only once
            comb.remove(comb.size() - 1);   // back-tracking
        }
    }
}