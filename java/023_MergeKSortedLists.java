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
    // sol 1: Divide & conquer, binary tree structure. On every level we actually traverse
    // all n numbers without repetition. And the level of tree is logk, so O(nlogk) Time.
    // version 1: recursive, top -> bottom. divide lists into two halves and merge them. 
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid); // merge first half
        ListNode right = mergeHelper(lists, mid + 1, end);  // merge second half
        return mergeTwoLists(left, right);  // merge the results of two halves
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {  // O(n) Time
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // // recursive sol
        // if (l1.val < l2.val) {
        //  l1.next = mergeTwoLists(l1.next, l2);
        //  return l1;
        // } else {
        //  l2.next = mergeTwoLists(l1, l2.next);
        //  return l2;
        // }
        
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
    }
        
    // version 2: iterative, bottom-up. merge lists in pair, and store the res for each level.
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        List<ListNode> newLists = Arrays.asList(lists);
        while (newLists.size() > 1) {
            List<ListNode> tmp = new ArrayList<ListNode>();
            for (int i = 0; i + 1 < newLists.size(); i += 2) {
                ListNode merged = mergeTwoLists(newLists.get(i), newLists.get(i + 1));
                tmp.add(merged);
            }
            if (newLists.size() % 2 == 1) {
                tmp.add(newLists.get(newLists.size() - 1));
            }
            newLists = tmp;
        }
        return newLists.get(0);
    }

    // sol 2: use priority queue (min-heap) to sort nodes by value. O(nlogk) Time.
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode pointer = dummy;
        for (ListNode l : lists) {
            if (l != null) {
                pq.add(l);
            }
        }
        while (!pq.isEmpty()) {
            pointer.next = pq.poll();
            pointer = pointer.next;
            if (pointer.next != null) {
                pq.add(pointer.next);
            }
        }
        return dummy.next;
    }
}