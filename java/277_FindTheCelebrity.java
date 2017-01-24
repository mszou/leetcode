/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */

public class Solution {
	public int findCelebrity(int n) {
		// idea: traverse to find a person that knows nobody after him as candidate for celebrity,
		// then traverse for the 2nd time to check whether he is celebrity.	O(n) calls, O(1) Space.
		int candidate = 0;
		for (int i = 1; i < n; i++) {
			if (knows(candidate, i)) {	// candidate knows i, so he/she cannot be celebrity
				candidate = i;	// let i be candidate (i > candidate)
			}
		}	// if we got candidate k after this for loop, it means 0 ~ k-1 cannot be the celebrity
		// because they know someone, k knows no one between k+1 and n-1, so k+1 ~ n-1`cannot be
		// the celebrity, either. Therefore, k is the only possible candidate for celebrity.
		// Then we need to check whether k is the celebrity or not in the second for loop. 
		for (int i = 0; i < n; i++) {
			if (i < candidate && knows(candidate, i) || !knows(i, candidate)) {
				return -1;	// k knows i (0 ~ k-1) or i doesn't know k, k is not celebrity
			}
			if (i > candidate && !knows(i, candidate)) {
				return -1;	// i (k+1 ~ n-1) doesn't know k
			}
		}
		return candidate;
	}
}