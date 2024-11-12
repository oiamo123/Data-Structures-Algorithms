package org.example;

import org.example.lists.DoublyLinkedList;
import org.example.lists.ListChallenges;
import org.example.nodes.ListNode;

public class Main {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n4.next = n5;
        n5.next = n6;

        ListChallenges challenges = new ListChallenges();
        challenges.mergeTwoLists(n1, n4);
    }
}