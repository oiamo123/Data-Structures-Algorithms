package org.example.hashset;

import java.util.LinkedList;

public class CustomHashSet {
    private LinkedList<Integer>[] hashSet;
    private int capacity;

    public CustomHashSet() {
        this.capacity = 1500;
        this.hashSet = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashSet[i] = new LinkedList<>();
        }
    }

    public void add(int value) {
        if (contains(value)) {
            return;
        }

        int key = hashFunction(value);
        hashSet[key].add(value);
    }

    public int get(int value) {
        int key = hashFunction(value);
        return hashSet[key].get(value);
    }

    public void remove(int value) {
        int index = hashFunction(value);
        LinkedList<Integer> bucket = hashSet[index];
        if (bucket.contains(value)) {
            bucket.remove(Integer.valueOf(value));  // Remove the element
        }
    }

    public boolean contains(int value) {
        int key = hashFunction(value);
        return hashSet[key].contains(value);
    }

    private int hashFunction(int value) {
        return Math.abs(value) % capacity;
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
