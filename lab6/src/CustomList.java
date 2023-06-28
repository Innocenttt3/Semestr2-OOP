import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomList<T> extends AbstractList<T> {
    public class Node {
        T data;
        Node nextOne;

        Node(T value) {
            this.data = value;
            this.nextOne = null;
        }
    }

    private Node tail;
    private Node head;

    public CustomList() {
        tail = null;
        head = null;
    }

    @Override
    public int size() {
        int counter = 0;
        Node tempNode = head;
        while (tempNode != null) {
            tempNode = tempNode.nextOne;
            counter++;
        }
        return counter;
    }

    @Override
    public T get(int index) {
        int tmpIndex = 0;
        if (index > size()) {
            throw new RuntimeException("index is out of size");
        }
        Node indexFinder = head;
        while (tmpIndex < index) {
            indexFinder = indexFinder.nextOne;
            tmpIndex++;
        }
        return indexFinder.data;
    }

    public void addLast(T newValue) {
        Node newNode = new Node(newValue);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.nextOne = newNode;
            tail = newNode;
        }
    }

    public T getLast() {
        return tail.data;
    }

    public void addFirst(T newValue) {
        Node newNode = new Node(newValue);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.nextOne = head;
            head = newNode;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node tmpNode = head;

            @Override
            public boolean hasNext() {
                if (tmpNode == null) {
                    return false;
                }
                return tmpNode.nextOne != null;
            }

            @Override
            public T next() {
                T previous = head.data;
                tmpNode = head.nextOne;
                return null;
            }
        };
    }

    public T getFirst() {
        return head.data;
    }

    public T removeFirst() {
        Node oldRoot = this.head;
        this.head = oldRoot.nextOne;
        return oldRoot.data;
    }

    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("Lista jest pusta.");
        }
        T value;
        if (head == tail) {
            value = head.data;
            head = null;
            tail = null;
        } else {
            Node prev = null;
            Node current = head;
            while (current.nextOne != null) {
                prev = current;
                current = current.nextOne;
            }

            value = current.data;
            prev.nextOne = null;
            tail = prev;
        }
        return value;
    }
}

