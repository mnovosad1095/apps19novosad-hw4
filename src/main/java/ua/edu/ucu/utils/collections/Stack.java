package ua.edu.ucu.utils.collections;

import ua.edu.ucu.utils.collections.immutable.ImmutableLinkedList;

public class Stack {
    private int size;
    private ImmutableLinkedList elements;

    public Stack() {
        size = 0;
        elements = new ImmutableLinkedList();
    }

    public Stack(Object[] arr) {
        size = arr.length;
        elements = new ImmutableLinkedList(arr);
    }

    public void push(Object e) {
        elements = elements.addFirst(e);
    }

    public Object pop() {
        Object first = elements.getFirst();
        elements = elements.removeFirst();

        return first;
    }

    public Object peek() {
        return elements.getFirst();
    }

    public int size() {
        return size;
    }
}
