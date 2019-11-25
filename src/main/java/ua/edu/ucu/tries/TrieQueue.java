package ua.edu.ucu.tries;

import java.util.Iterator;

import ua.edu.ucu.utils.collections.Queue;

public class TrieQueue implements Iterable<String> {
    private Queue q;
    private TrieIterator iterator;

    public TrieQueue() {
        q = new Queue();
        iterator = new TrieIterator(q);
    }

    public TrieQueue(String[] elements) {
        q = new Queue(elements);
        iterator = new TrieIterator(q);
    }

    public String peek() {
        return (String) q.peek();
    }

    public void enqueue(String el) {
        q.enqueue(el);
    }

    public String dequeue() {
        return (String) q.dequeue();
    }

    public int size() {
        return q.size();
    }

    @Override
    public Iterator<String> iterator() {
        return iterator;
    }
}