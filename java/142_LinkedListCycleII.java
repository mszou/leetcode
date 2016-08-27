/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 * Follow up:
 * Can you solve it without using extra space?
 */


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        // idea: use two pointers and take a 2-round walk: 
        // 1. if fast & slow pointers meet at some point, then it has a cycle. 
        // 2. one pointer starts from the begining, another pointer starts from the first meeting node, 
        // move both pointers one step at a time, they'll meet each other at the start of the cycle.
        // Proof: Suppose pointers in the first round meet at step k, the distance between the start node of list and 
        // the start node of cycle is s, and the distance between the start node of cycle and the first meeting node is m,
        // the circumference of the cycle is r. Then 2k = (s + m + n1r) = 2(s + m + n2r) => s + m = nr. (n1, n2, n ∈ N)
        // Steps moving from start node to the start of the cycle is just s. take another s steps from m, you would reach 
        // the start of the cycle, covering n cycles. In other words, pointers in 2nd round will meet at the entry of cycle.
        if (head == null || head.next == null) {
            return null;
        }
        // // version 1
        // ListNode fast = head.next;
        // ListNode slow = head;
        // while (fast != slow) {
        //     if (fast == null || fast.next == null) {
        //         return null;
        //     }
        //     fast = fast.next.next;
        //     slow = slow.next;
        // }
        // while (head != slow.next) {
        //     head = head.next;
        //     slow = slow.next;
        // }
        // return head;
        
        // version 2
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {	// first round meet
                ListNode slow2 = head;	// slow2 start from the beginning
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}