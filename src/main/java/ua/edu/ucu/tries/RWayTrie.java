package ua.edu.ucu.tries;

import ua.edu.ucu.tries.TrieQueue;
import ua.edu.ucu.utils.collections.Queue;;

public class RWayTrie implements Trie {

    private static final int R = 256;
    private Node root;
    private int size;

    private static class Node {
        private Tuple value;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        if (contains(t.getTerm())) size--;
        root = put(root, t, 0);
        size++;
    }

    private Node put(Node node, Tuple t, int d) {
        if (node == null) {
            node = new Node();
        }
        if (t.getTerm().length() == d) {
            node.value = t;
            return node;
        }
        char c = t.getTerm().charAt(d);
        node.next[c] = put(node.next[c], t, d + 1);
        return node;
    }

    public Node get(String key) {
        Node node = get(root, key, 0);
        if (node == null)
            return null;
        return node;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    @Override
    public boolean contains(String word) {
        Node r = get(word);
        return r != null && r.value != null;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        if (root == null) {
            return false;
        }
        size--;
        return true;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.value = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.value != null) {
            return x;
        }

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x; 
            }
        }

        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        TrieQueue q = new TrieQueue();
        collect(get(root, s, 0), s, q);
        return q;
    }

    private void collect(Node x, String s, TrieQueue words) {
        // if (x == null)
        //     return;
        // if (x.value != null)
        //     q.enqueue(s);
        // for (char c = 0; c < R; c++)x
        //     collect(x.next[c], s + c, q);
        Queue queue = new Queue();
        Node nd;
        queue.enqueue(x);
        

        while (queue.peek() != null) {
            nd = (Node) queue.dequeue();

            if (nd.value != null) {
                words.enqueue(nd.value.getTerm());
            }
            if (nd != null) {
                System.out.println(nd.next.length);
                for (Node node : nd.next) {
                    
                    if (node != null) {queue.enqueue(node);}
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

}
