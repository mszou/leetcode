/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 */

 public class Solution {
    // idea: observe the pattern, there are (n-1)! permutations starting with 1, same for 2,3,...
    // and fix first i digits, there are (n-i)! permutations. Therefore, for the kth permutation, 
    // it must have the first digit i : (i-1)*(n-1)! < k <= i * (n-1)!, and so on
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(); // list of currently available numbers
        int[] factorial = new int[n];
        StringBuilder sb = new StringBuilder();
        // create an array of factorial lookup, i.e. {1, 1, 2, 6, ... , (n-1)!}
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
        	factorial[i] = factorial[i-1] * i;
        }
        for (int i = 1; i <= n; i++) {
        	nums.add(i);
        }
        for (int i = n - 1; i > 0; i--) {
        	int index = (k - 1) / factorial[i];
        	sb.append(nums.get(index));
        	nums.remove(index);
        	k -= index * factorial[i];
        }
        sb.append(nums.get(0));
        return sb.toString();
    }
}