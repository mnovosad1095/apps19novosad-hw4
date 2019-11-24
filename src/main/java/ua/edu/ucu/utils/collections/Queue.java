package ua.edu.ucu.utils.collections;

import java.util.Iterator;

import ua.edu.ucu.tries.TrieIterator;
import ua.edu.ucu.utils.collections.immutable.ImmutableLinkedList;

public class Queue implements Iterable<String> {
    private int size;
    private ImmutableLinkedList elements;
    private TrieIterator iterator;

    public Queue() {
        size = 0;
        elements = new ImmutableLinkedList();
        iterator = new TrieIterator(this);
    }

    public Queue(Object[] arr) {
        size = arr.length;
        elements = new ImmutableLinkedList(arr);
    }

    

    public void enqueue(Object e) {
        elements = elements.addFirst(e);
        size++;
    }

    public Object dequeue() {
        Object last = elements.getLast();
        elements = elements.removeLast();
        size--;
        return last;
    }

    public Object peek() {
        Object last = elements.getLast();
        return last;
    }

    public int size() {
        return size;
    }

    public Object[] getElements() {
        return elements.toArray();
    }

    @Override
    public Iterator<String> iterator() {
        return iterator;
    }
}
