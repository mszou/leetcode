/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * Hint:
 * An easy approach is to sort the array first.
 * What are the possible values of h-index?
 * A faster approach is to use extra space.
 */

public class Solution {
    public int hIndex(int[] citations) {
        // idea: use an array ("counts") of length n+1, counts[i] records the number of papers
        // that each have i citations, in this way, the sum from counts[i] to counts[n] equals
        // to the number of papers having at least i citations. 	O(n) Time, O(n) Space.
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int len = citations.length;
        int[] counts = new int[len + 1];   
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len) {  // if # citations exceeds len
                counts[len]++;	// counts[len] records # papers having at least len citations
            } else {
                counts[citations[i]]++;
            }
        }
        int sum = 0;
        for (int i = len; i >= 0; i--) {
            sum += counts[i];	// add up the counts from right to left
            if (sum >= i) {	// once find one eligible value, that is the h-index
                return i;
            }
        }
        return 0;
    }
}