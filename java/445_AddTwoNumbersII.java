/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// idea: use stack, push nodes in two list into two stacks, Every time we pop one from each stack,
		// compute their sum for the node in res and leave the carry for next round.	O(n) Time, O(n) Space.
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while (l1 != null) {	// push stack1
			s1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {	// push stack2
			s2.push(l2.val);
			l2 = l2.next;
		}
		int sum = 0;	// for addition of current position
		ListNode curr = new ListNode(0);
		while (!s1.empty() || !s2.empty()) {
			if (!s1.empty()) {
				sum += s1.pop();
			}
			if (!s2.empty()) {
				sum += s2.pop();
			}
			curr.val = sum % 10;
			ListNode prev = new ListNode(sum / 10);
			prev.next = curr;
			curr = prev;
			sum /= 10;
		}
		return curr.val == 0 ? curr.next : curr;
	}
}