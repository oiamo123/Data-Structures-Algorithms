package org.example.lists;

import org.example.nodes.DoublyNode;

public class DoublyLinkedList {
    private int size;
    private DoublyNode head;
    private DoublyNode tail;

    public int val;
    public DoublyNode next;
    public DoublyNode prev;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        this.size = 0;
    }

    public int get(int index) { // get by index
        if (index < 0 || index >= size) return -1;

        DoublyNode node = head;

        for (int i = 0; i < index; i++) { // loop over list
            node = node.next;
        }

        return node.val; // return value
    }

    public void addAtHead(int val) {
        DoublyNode node = new DoublyNode(val);
        if (head == null) { // if the head is null, set the new node as the head and tail
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }

        size++; // increase size by one
    }

    public void addAtTail(int val) {
        DoublyNode node = new DoublyNode(val);
        if (tail == null) { // if the tail is null, set the tail and head as the new node
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return; // out of bounds
        if (index == 0) { // add at head conditional
            addAtHead(val);
        } else if (index == size) { // add at tail conditional
            addAtTail(val);
        } else {
            DoublyNode node = new DoublyNode(val);
            DoublyNode current;

            if (index > size / 2) { // check if the index is closer to the tail of head
                current = tail;
                for (int i = size - 1; i > index - 1; i--) { // loop backwards from tail
                    current = current.prev;
                }
            } else {
                current = head;
                for (int i = 0; i < index - 1; i++) {  // loop forwards from head
                    current = current.next;
                }
            }

            // insert new node
            node.next = current.next;
            if (current.next != null) current.next.prev = node;
            current.next = node;
            node.prev = current;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        // similar to add at index method
        // head if the index is the head or tail
        // otherwise determine if the index is closer to the head or tail
        if (index == 0) {
            if (head == null) return;
            head = head.next;
            if (head != null) head.prev = null;
            if (size == 1) tail = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            DoublyNode current;
            if (index > size / 2) {
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            } else {
                current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            }
            current.prev.next = current.next;
            if (current.next != null) current.next.prev = current.prev;
        }

        size--;
    }
}
