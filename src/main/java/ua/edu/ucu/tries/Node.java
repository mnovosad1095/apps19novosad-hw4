package ua.edu.ucu.tries;


public class Node {
    private Tuple value;
    private Node[] next = new Node[26];

    public void setNext(Node next, char c) {
        this.next[c-97] = next;
    }

    public void setValue(Tuple val) {
        value = val;
    }

    public Tuple getValue() {
        return value;
    }

    public Node getNext(char c) {
        System.out.println( c);
        for (Node node : next) {
            System.out.println(node);
        }
        return next[c-97];
    }
}
