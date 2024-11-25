package org.example.StacksAndQueues;

public class CircularQueue {
    int[] data;
    int size;
    int head;
    int tail;

    public CircularQueue(int k) {
        this.data = new int[k];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        data[tail] = value;
        if (tail == data.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
        size++;

        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        data[head] = 0;
        if (head == data.length - 1) {
            head = 0;
        } else {
            head++;
        }
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return data[tail == 0 ? data.length - 1 : tail - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }
}
