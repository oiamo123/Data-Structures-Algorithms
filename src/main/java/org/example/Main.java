package org.example;

import org.example.hashmap.HashMapChallenges;
import org.example.lists.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        list.get(1);
        list.deleteAtIndex(1);
        list.deleteAtIndex(0);
        System.out.println(list);
    }
}