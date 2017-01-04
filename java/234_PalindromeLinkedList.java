/**
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
    	// idea: reverse half of the list, then compare it with the other half. O(n) time, O(1) space
    	// use two pointers, fast & slow, to find the middle point first
        if (head == null || head.next == null) {
            return true;
        }
        // sol 1: reverse first half
        ListNode fast = head, slow = head;
        ListNode reverse = slow.next;	
        ListNode pre = slow;
        // reversion: "pre(slow) -> reverse -> X " => "pre <- slow - X(reverse)"
        // find middle point, and reverse the first half
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = reverse;
            reverse = reverse.next;
            slow.next = pre;
        }
        // if the length of list is odd, move slow one step to the left (skip the middle node)
        if (fast.next == null) {
            slow = slow.next;
        }       
        // compare from mid to head/tail (i.e. slow --> head vs. reverse --> tail)
        while (reverse != null) {
            if (slow.val != reverse.val) {
                return false;
            }
            slow = slow.next;
            reverse = reverse.next;
        }
        return true;
    }
        
    //     // sol 2: reverse second half
    //     ListNode fast = head;
    //     ListNode slow = head;

    //     while (fast != null && fast.next != null) {
    //         fast = fast.next.next;
    //         slow = slow.next;
    //     }
    //     if (fast != null) slow = slow.next;

    //     slow = reverse(slow);
    //     while (slow != null && head.val == slow.val) {
    //         head = head.next;
    //         slow = slow.next;
    //     }
    //     return slow == null;
    // }
    
    // private ListNode reverse(ListNode head) {
    //     ListNode prev = null;
    //     while (head != null) {
    //         ListNode next = head.next;
    //         head.next = prev;
    //         prev = head;
    //         head = next;
    //     }
    //     return prev;
    // }
}