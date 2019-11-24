package ua.edu.ucu.utils.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {

    static class Node {

        public Object data;
        public Node next;

        Node(Object d) {
            data = d;
            next = null;
        }

        public Node copy() {
            return new Node(data);
        }
        
        @Override
        public boolean equals(Object target) {
            return data == target;
        }
    }

    public Node head;
    private int size;

    public ImmutableLinkedList() {
        size = 0;
        head = null;
    }

    public ImmutableLinkedList(Object e) {
        size = 1;
        Node nd = new Node(e);
        head = nd;
    }

    public ImmutableLinkedList(Object[] arr) {
        int len = arr.length;
        size = len;
        head = new Node(arr[0]);

        Node previous = head;
        for (int i = 1; i < len; i++) {
            Node nd = new Node(arr[i]);
            previous.next = nd;
            previous = nd;
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return addAll(size, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        return copy(2, index, c, null);
    }

    @Override
    public Object get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (((Integer)size).equals(0)) return null;

        int i = 0;
        Node currNode = head;

        while (i != index) {
            currNode = currNode.next;
            i++;
        }

        return currNode.data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        return copy(0, index, null, null);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        return copy(1, index, null, e);
    }

    @Override
    public int indexOf(Object e) {
        if (size == 0) {
            return -1;
        }

        Node currNode = head;
        int i = 0;

        while (currNode != null) {
            if (currNode.data.equals(e)) {
                return i;
            }
            i++;
            currNode = currNode.next;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        final ImmutableLinkedList newList = new ImmutableLinkedList();
        return newList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[size];
        // System.out.println("Size: " + size);

        Node currNode = head;
        int i = 0;
        while (i != size) {
            elements[i] = currNode.data;
            // System.out.println(currNode.data);
            i++;
            currNode = currNode.next;
        }

        return elements;
    }

    @Override
    public String toString() {
        return new String();
    }

    public ImmutableLinkedList copy() {
        if (size == 0) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newList = new ImmutableLinkedList(head.data);

        Node currNode1 = head;
        Node currNode2 = newList.head;

        while (currNode1 != null) {
            Node nd = new Node(currNode1.data);
            currNode2.next = nd;
            currNode2 = currNode2.next;
            currNode1 = currNode1.next;
        }
        newList.size = size;

        return newList;
    }


    public ImmutableLinkedList addFirst(Object e) {
        
        return add(0,e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        if (head != null) {
            return head.data;
        }

        return null;
    }

    public Object getLast() {
        Object e = get(size-1);
        return e;
    }

    public ImmutableLinkedList removeFirst() {
        
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size-1);
    }

    private ImmutableLinkedList copy(int caseType, int index, Object[] elements, Object e) {
        Node selfNode = head;
        ImmutableLinkedList newList = new ImmutableLinkedList();
        newList.head = new Node(null);
        Node currNode = newList.head;

        int i = 0;
        while (i < index) {
            currNode.next = new Node(selfNode.data);
            currNode = currNode.next;
            selfNode = selfNode.next;
            newList.size++;
            i++;
        }
        
        if (caseType == 0) {
            selfNode = selfNode.next;
            // newList.size -= 1;
        } else if (caseType == 1) {
            currNode.next = new Node(e);
            currNode = currNode.next;
            selfNode = selfNode.next;
        } else if (caseType == 2) {
            for (Object el : elements) {
                currNode.next = new Node(el);
                currNode = currNode.next;
            }
            newList.size += elements.length;
        }

        while (selfNode != null) {
            currNode.next = new Node(selfNode.data);
            currNode = currNode.next;
            selfNode = selfNode.next;
            newList.size++;
        }
        newList.head = newList.head.next;
        // System.out.println("Head "+newList.head.next.data);
        
        return newList;
    }
}
