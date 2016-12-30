/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗            
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	// sol 1: get the length of two lists, compute the difference and advance the pointer
	// for the longer one s.t. they have same length, then move 2 pointers together
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
        	return null;
        }
        int lenA = getLength(headA), lenB = getLength(headB);
        while (lenA > lenB) {
        	headA = headA.next;
        	lenA--;
        }
        while (lenA < lenB) {
        	headB = headB.next;
        	lenB--;
        }
        while (headA != headB) {
        	headA = headA.next;
        	headB = headB.next;
        }
        return headA;
	}

	private int getLength(ListNode node) {
		int length = 0;
		while (node != null) {
			node = node.next;
			length++;
		}
		return length;
	}
    
	// sol 2: a briliant idea: suppose length of list A is a+c, length of list B is b+c,
	// they have an intersection list C of length c. Then 2 pointers traverse two lists,
	// when reaching the end, move to the head of the other list and continue going.
	// The pointers will meet at the beginning of intersection, because a+c+b = b+c+a.
	// If no intersection, the two pointers will become null at the same time.
	// As long as there is no cycle in the lists, at most 2 round traversal is needed.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
        	return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
        	a = (a == null) ? headB : a.next;
        	b = (b == null) ? headA : b.next;
        }
        return a;
    }

    // idea 3: concatenate List B to the tail of List A, then find the start of the cycle
}