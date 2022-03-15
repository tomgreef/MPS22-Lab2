package queue;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue {
    private DequeNode<T> first, last;
    private int size;

    public DoubleLinkedListQueue() {
        first = last = null;
        size = 0;
    }

    @Override
    public void append(DequeNode node) {
        if (node == null)
            throw new RuntimeException("Node is null");

        if (first == null)
            last = first = node;
        else {
            node.setNext(first);
            first.setPrevious(node);
            first = node;
        }
        size++;
    }

    @Override
    public void appendLeft(DequeNode node) {
        if (node == null)
            throw new RuntimeException("Node is null");

        if (last == null)
            first = last = node;
        else {
            node.setPrevious(last);
            last.setNext(node);
            last = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        first = first.getNext();

        if (first == null)
            last = null;
        else
            first.setPrevious(null);

        size--;
    }

    @Override
    public void deleteLast() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        last = last.getPrevious();

        if (last == null)
            first = null;
        else
            last.setNext(null);

        size--;
    }

    @Override
    public DequeNode peekFirst() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        return first;
    }

    @Override
    public DequeNode peekLast() {
        if (size() == 0)
            throw new RuntimeException("Queue is empty");

        return last;
    }

    @Override
    public int size() {
        return size;
    }
}
