package org.example.lists;

import java.util.HashSet;
import java.util.Set;

public class SinglyLinkedChallenges {
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

    public ListNode getIntersectionNodePointer(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0; // initialize lengths
        ListNode nodeA = headA, nodeB = headB;

        while (nodeA != null && nodeA.next != null) {
            lenA++;
            nodeA = nodeA.next;
        }

        while (nodeB != null && nodeB.next != null) {
            lenB++;
            nodeB = nodeB.next;
        }

        int diff = lenA - lenB;
        nodeA = headA;
        nodeB = headB;

        if (diff > 0) {
            while (diff > 0) {
                nodeA = nodeA.next;
                diff--;
            }
        } else if (diff < 0) {
            while (diff < 0) {
                nodeB = nodeB.next;
                diff++;
            }
        }

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
        ListNode first = head;
        ListNode second = head;

        if (head.next == null) {
            return null;
        }

        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        if (first == null) {
            return head.next;
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
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
}
