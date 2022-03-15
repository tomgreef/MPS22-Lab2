package queue;

public class DoubleLinkedListQueue implements DoubleEndedQueue {
    private DequeNode head, tail;
    private int size;

    public DoubleLinkedListQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void append(DequeNode node) {
        if (node == null)
            throw new RuntimeException("Node is null");

        if (head == null)
            tail = head = node;
        else {
            node.setNext(tail);
            tail.setPrevious(node);
            tail = node;
        }
        size++;
    }

    @Override
    public void appendLeft(DequeNode node) {
        if (node == null)
            throw new RuntimeException("Node is null");

        if (head == null)
            head = tail = node;
        else {
            node.setPrevious(head);
            head.setNext(node);
            head = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        head = head.getNext();

        if (head == null)
            tail = null;
        else
            head.setPrevious(null);

        size--;
    }

    @Override
    public void deleteLast() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        tail = tail.getPrevious();

        if (tail == null)
            head = null;
        else
            tail.setNext(null);

        size--;
    }

    @Override
    public DequeNode peekFirst() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        return head;
    }

    @Override
    public DequeNode peekLast() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        return tail;
    }

    @Override
    public int size() {
        return size;
    }
}
