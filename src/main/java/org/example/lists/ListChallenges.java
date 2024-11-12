package org.example.lists;

import org.example.nodes.ListNode;

import java.util.HashSet;
import java.util.Set;

public class ListChallenges {
    /*
    There are 2 solutions 2 this:
    A) Use a hashmap: Uses less memory
    B) Pointer method: Faster
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>(); // set with all nodes
       ListNode node = headA; // set starting node to headA

        while (node != null) { // while node != null
            set.add(node); // add the node to the set
            node = node.next; //
        }

        node = headB; // set the node to head b
        while (node != null) {
            if (set.contains(node)) { // if the set contains the node, return that node
                return node;
            }
            node = node.next; // go to the next node
        }

        return null;
    }

    // This is the pointer method
    public ListNode getIntersectionNodePointer(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0; // initialize lengths
        ListNode nodeA = headA, nodeB = headB;

        while (nodeA != null && nodeA.next != null) { // get the length of list1
            lenA++;
            nodeA = nodeA.next;
        }

        while (nodeB != null && nodeB.next != null) { // get the length of list 2
            lenB++;
            nodeB = nodeB.next;
        }

        int diff = lenA - lenB; // find the difference in lengths
        nodeA = headA; // reset the nodes
        nodeB = headB;

        if (diff > 0) { // check if diff is positive or negative
            while (diff > 0) {
                nodeA = nodeA.next; // if diff is positive (meaning list a is longer), offset list a
                diff--;
            }
        } else if (diff < 0) {
            while (diff < 0) {
                nodeB = nodeB.next; // if diff is negative (meaning list b is longer), offset list b
                diff++;
            }
        }

        // loop over both lists until node a and b match
        // return either the node or null
        while (nodeA != null && nodeB != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        return null;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head; // initialize some variables
        ListNode second = head;

        if (head.next == null) {
            return null;
        }

        for (int i = 0; i < n; i++) { // increase the first list nth times
            first = first.next;
        }

        if (first == null) {
            return head.next;
        }

        while (first.next != null) { // loop until first gets to the end of the list
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next; // remove the desired node by setting the second to next.next
        return head;
    }

    public ListNode reverseList(ListNode head) {
        // set head.next to head.next.next
        // set newhead to head.next
        // set head.next.next to head
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp;
        ListNode prev = head;

        while (head.next != null) {
            temp = head.next; // set temp to head.next
            head.next = temp.next; // set the head.next to head.next.next
            temp.next = prev;
            prev = temp;
        }

        return prev;
    }

    // This is my solution
    public ListNode removeElements(ListNode head, int val) {
        /*
        However some of the example on leetcode used recursion which I haven't visited yet but looks a bit like this:

        if (head.next == null) return null;
        head.next = removeElements(head.next, val)
        return head.val == val ? head.next: head;

        Essentially it loops through the entire list until it gets to the end, and then on the way back up (return statements), it'll return
        either the head or head.next
         */

        // if the head is null, return null
        if (head == null) return null;

        ListNode current = head; // keep track of the current node
        ListNode prev; // keep track of the previous node

        while (current.val == val) {
            if (current.next == null) return null;

            current = current.next;
            head = current;
        }

        while (current.next != null) { // while the next node does not equal null
            prev = current; // set previous to current
            current = current.next; // set current to the next node

            if (current.val == val) { // if the current value == to val to be removed
                if (current.next == null) { // if the current.next = null, set the prev.next to null
                    prev.next = null;
                    return head; // return head
                }

                prev.next = current.next; // remove the node
                current = prev;
            }
        }

        return head;
    }

    // 1, 2, 4
    // 1, 3, 4
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // initialize a dummy node to help simplify list handling
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        // traverse both lists until we reach the end of one
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }

        // attach any remaining nodes from either list1 or list2
        if (list1 != null) {
            head.next = list1;
        } else if (list2 != null) {
            head.next = list2;
        }

        // return the merged list, which starts from dummy.next
        return dummy.next;
    }
}
