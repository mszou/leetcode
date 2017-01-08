/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
    // sol 1: Divide and conquer. Time: O(nlogk). (mergeTwoLists is O(n))
    // recursively divide lists into two halves and merge them
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // non-recursive sol
        ListNode dummy = new ListNode(0);
        ListNode pointer = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pointer.next = l1;
                l1 = l1.next;
            } else {
                pointer.next = l2;
                l2 = l2.next;
            }
            pointer = pointer.next;
        }
        if (l1 != null) {
            pointer.next = l1;
            l1 = l1.next;
        } 
        if (l2 != null) {
            pointer.next = l2;
            l2 = l2.next;
        }
        return dummy.next;
        
        // // recursive sol
        // if (l1.val < l2.val) {
        //  l1.next = mergeTwoLists(l1.next, l2);
        //  return l1;
        // } else {
        //  l2.next = mergeTwoLists(l1, l2.next);
        //  return l2;
        // }
    }
        
    // // sol 2: merge two by two.
    // public ListNode mergeKLists(ListNode[] lists) {
    //     if (lists.length == 0) {
    //         return null;
    //     }
    //     List<ListNode> newLists = Arrays.asList(lists);
    //     while (newLists.size() > 1) {
    //         List<ListNode> tmp = new ArrayList<ListNode>();
    //         for (int i = 0; i + 1 < newLists.size(); i += 2) {
    //             ListNode merged = mergeTwoLists(newLists.get(i), newLists.get(i+1));
    //             tmp.add(merged);
    //         }
    //         if (newLists.size() % 2 == 1) {
    //             tmp.add(newLists.get(newLists.size() - 1));
    //         }
    //         newLists = tmp;
    //     }
    //     return newLists.get(0);
    // }
    

    // sol 3: priority queue (heap)
}