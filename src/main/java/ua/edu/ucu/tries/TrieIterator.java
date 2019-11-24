package ua.edu.ucu.tries;

import java.util.Iterator;

import ua.edu.ucu.utils.collections.Queue;

public class TrieIterator implements Iterator<String> {
    private Queue queue;

    public TrieIterator(Queue q) {
        queue = q;
    }

    @Override
    public boolean hasNext() {
        return queue.peek() != null;
    }

    @Override
    public String next() {
        String res = (String) queue.dequeue();
        return res;
    }

}