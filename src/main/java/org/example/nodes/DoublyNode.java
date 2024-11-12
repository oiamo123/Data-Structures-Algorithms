package org.example.nodes;

public class DoublyNode {
    public int val;
    public DoublyNode next;
    public DoublyNode prev;

    public DoublyNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}
