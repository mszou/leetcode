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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode pointer = head;
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
        return head.next;
    }
}