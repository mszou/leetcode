/**
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
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
    // The most significant digit comes last and the least significant digit comes first
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // idea: add nodes pair by pair, record the sum and carry, when reach the end of
        // one list, add the rest of the other list & carry until reach end and no carry
        // O(m+n) Time, O(max(m,n)) Space.
        ListNode dummy = new ListNode(0);   // add a head node for return list
        ListNode pointer = dummy;   // pointer for constucting result
        int sum = 0, carry = 0;
        while (l1 != null && l2 != null) {
        	sum = l1.val + l2.val + carry;
        	pointer.next = new ListNode(sum % 10);
        	carry = sum / 10;
        	l1 = l1.next;
        	l2 = l2.next;
        	pointer = pointer.next;
        }
        while (l1 != null) {
        	sum = l1.val + carry;
        	pointer.next = new ListNode(sum % 10);
        	carry = sum / 10;
        	l1 = l1.next;
        	pointer = pointer.next;
        }
        while (l2 != null) {
        	sum = l2.val + carry;
        	pointer.next = new ListNode(sum % 10);
        	carry = sum / 10;
        	l2 = l2.next;
        	pointer = pointer.next;
        }
        if (carry != 0) {
        	pointer.next = new ListNode(carry);
        }
        return dummy.next;
    }
}