package org.example.StacksAndQueues;

import java.util.ArrayList;
import java.util.List;

public class MinStack {
    private List<Integer> stack;
    private List<Integer> minStack;

    public MinStack() {
        stack = new ArrayList<>();
        minStack = new ArrayList<>();
    }

    public void push(int val) {
        stack.add(val);
        if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1)) {
            minStack.add(val);
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        int removed = stack.remove(stack.size() - 1);
        if (removed == minStack.get(minStack.size() - 1)) {
            minStack.remove(minStack.size() - 1);
        }
    }

    public int top() {
        if (stack.isEmpty()) return -1;
        return stack.get(stack.size() - 1);
    }

    public int getMin() {
        if (minStack.isEmpty()) return -1;
        return minStack.get(minStack.size() - 1);
    }
}
