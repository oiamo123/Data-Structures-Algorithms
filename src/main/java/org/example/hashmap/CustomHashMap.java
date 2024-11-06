package org.example.hashmap;

import java.util.LinkedList;

public class CustomHashMap {
    private LinkedList<Entry>[] table;
    private int capacity;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.capacity = 1500; // realistically would set it to like 32 items
        this.table = new LinkedList[capacity];
    }

    private static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(int key, int value) {
        int pos = hashFunction(key);

        if (table[pos] == null) {
            table[pos] = new LinkedList<>();
        }

        for (Entry entry : table[pos]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        table[pos].add(new Entry(key, value));
    }

    public int get(int key) {
        int pos = hashFunction(key);

        if (table[pos] == null) {
            return -1;
        }

        for (Entry entry : table[pos]) {
            if (entry.key == key) {
                return entry.value;
            }
        }

        return -1;
    }

    public void remove(int key) {
        int pos = hashFunction(key);
        if (table[pos] == null) {
            return;
        }

        table[pos].removeIf(entry -> entry.key == key);
    }

    private int hashFunction(int key) {
        return key % capacity;
    }
}
