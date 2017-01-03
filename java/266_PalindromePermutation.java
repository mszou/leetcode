/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example,
 * "code" -> False, "aab" -> True, "carerac" -> True.
 * Hint:
 * Consider the palindromes of odd vs even length. What difference do you notice?
 * Count the frequency of each character.
 * If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
 */

public class Solution {
	public boolean canPermutePalindrome(String s) {
		// idea: if there is at most 1 character that occurs odd number of times,
		// then true, otherwise, false. Use a set to record the parity for each character.
		Set<Character> set = new HashSet<Character>();
		for (char c : s.toCharArray()) {
			if (!set.add(c)) {	// already exist
				set.remove(c);	// even number of time occurrence, so remove
			}
		}
		return set.size() <= 1;
	}
}