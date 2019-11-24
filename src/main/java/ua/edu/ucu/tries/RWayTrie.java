package ua.edu.ucu.tries;

import java.util.Arrays;

import ua.edu.ucu.utils.collections.Queue;

public class RWayTrie implements Trie {

    private Node root;
    private int size;

    private static class Node {
        private Tuple value;
        private Node[] next = new Node[256];
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t, 0);
        size++;
    }

    private Node put(Node node, Tuple t, int d) {
        if (node == null) {node = new Node();}
        System.out.println(t.term +" "+ d);
        if (t.getTerm().length() == d){
            node.value = t;
            return node;
        } 
        char c = t.getTerm().charAt(d);
        node.next[c] = put(node.next[c], t, d+1);
        return node;
    }

    public Node get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return node;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1); 
    }

    @Override
    public boolean contains(String word) {
        return get(word)!= null;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        if (root == null) return false;
        size--;
        return true;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            x.value = null;
        }else{
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        if (x.value != null) return x;

        for (char c = 97; c < 123; c++) {
            if (x.next[c] != null) return x;
        }

        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(get(root, s, 0), s, q);
        String[] words = new String[q.size()];
        Object[] q_els = q.getElements();
        
        for (int i = 0; i < q.size(); i++) {
            words[i] = (String) q_els[i]; 
        }

        return Arrays.asList(words);
    }
    
    private void collect(Node x, String s, Queue q) {
        if (x == null) return;
        if (x.value != null) q.enqueue(s);
        for (char c = 97; c < 123; c++)
            collect(x.next[c], s + c, q);
    }

    @Override
    public int size() {
        return size;
    }

}
