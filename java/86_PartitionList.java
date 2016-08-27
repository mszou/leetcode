/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
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
    public ListNode partition(ListNode head, int x) {
        // idea: add nodes to two lists separately, then join them
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallerDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode smaller = smallerDummy, greater = greaterDummy;
        while (head != null) {
            if (head.val < x) {
                smaller.next = head;
                smaller = head;
            } else {
                greater.next = head;
                greater = head;
            }
            head = head.next;
        }
        greater.next = null;
        smaller.next = greaterDummy.next;
        return smallerDummy.next;
    }
}