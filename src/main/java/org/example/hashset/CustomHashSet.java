package org.example.hashset;

import java.util.LinkedList;

public class CustomHashSet {
    private LinkedList<Integer>[] hashSet; // An array of linked lists in case there are any collisions
    private int capacity; // capacity of the list

    public CustomHashSet() {
        this.capacity = 1500; // capacity of the hashset, usually set to 16
        this.hashSet = new LinkedList[capacity]; // initialize the hashset
        for (int i = 0; i < capacity; i++) { // create a new hashset
            hashSet[i] = new LinkedList<>();
        }
    }

    // to add an item
    public void add(int value) {
        if (contains(value)) { // check if the set has the value already
            return;
        }

        int key = hashFunction(value); // create a key using the value
        hashSet[key].add(value); // add the value to the linkedlist at the keys index
    }

    public int get(int value) {
        int key = hashFunction(value); // use the value to get the key
        return hashSet[key].get(value); // access the linkedlist at the keys index
    }

    public void remove(int value) {
        int index = hashFunction(value); // get the key using the value
        LinkedList<Integer> bucket = hashSet[index]; // get the linked list at that index
        if (bucket.contains(value)) {
            bucket.remove(Integer.valueOf(value));  // remove the element
        }
    }

    public boolean contains(int value) {
        int key = hashFunction(value);
        return hashSet[key].contains(value);
    }

    // Converts a value into a unique key
    private int hashFunction(int value) {
        return Math.abs(value) % capacity; // this is a poor mans hash function
    }

    // Unused but would be placed inside of add method
    // Would keep track of size realistically double it
    // whenever the capacity reaches the load factor
    private void resize() {
        this.capacity *= 2;
        LinkedList<Integer>[] newHashTable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashTable[i] = new LinkedList<>();
        }

        for (LinkedList<Integer> bucket : hashSet) {
            for (int value : bucket) {
                int key = hashFunction(value);
                newHashTable[key].add(value);
            }
        }

        hashSet = newHashTable;
    }
}
