package org.example.lists;
// [] -> [] -> [] -> [] -> [];
/*
Considerations:
- Add a tail: private CustomNode tail
- Could also check to see if the index is closer to the tail
  or the head and iterate from there alternatively
 */
public class SinglyLinkedList {
    private CustomNode head;
    private int size;

    // Custom node class
    private static class CustomNode {
        private int data;
        private CustomNode next;

        public CustomNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int num) {
            this.data = num;
        }

        public CustomNode getNext() {
            return next;
        }

        public void setNext(CustomNode next) {
            this.next = next;
        }
    }

    public SinglyLinkedList() { // initialize size
        this.size = 0;
        this.head = null;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        CustomNode current = head; // start at head
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void addAtHead(int val) {
        CustomNode newHead = new CustomNode(val);
        newHead.setNext(head);
        head = newHead;
        size++;
    }

    public void addAtTail(int val) {
        CustomNode newTail = new CustomNode(val);
        if (head == null) { // if the list is empty
            head = newTail;
        } else {
            CustomNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newTail);
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        CustomNode newNode = new CustomNode(val); // create a new node
        CustomNode current = head; // start at the head

        if (index == 0) { // if the index is 0 set it as the head
            newNode.setNext(head);
            head = newNode;
        } else { // else iterate through nodes
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext()); // set the new nodes next to the previous' next
            current.setNext(newNode); // set the previous next to the new node
        }
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        CustomNode current = head;

        if (index == 0) {
            head = head.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        size--;
    }
}
