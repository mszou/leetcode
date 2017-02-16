/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input:
 * 1->2->3
 * Output:
 * 1->2->4
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
	public ListNode plusOne(ListNode head) {
		// sol 1: naive, reverse the list, plus 1 to the front, then reverse the result.

		// sol 2: while traversing, use a pointer to record the pos of last non-nine number
		// plus one to that number, and change the following continuous 9's into 0's
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode lastNotNine = dummy, pointer = head;
		while (pointer != null) {
			if (pointer.val != 9) {
				lastNotNine = pointer;
			}
			pointer = pointer.next;
		}
		lastNotNine.val++;	// increase the last non-nine number
		pointer = lastNotNine.next;
		while (pointer != null) {	// change the continuous 9's in the end into 0's
			pointer.val = 0;
			pointer = pointer.next;
		}
		return dummy.val == 1 ? dummy : dummy.next;
	}
}