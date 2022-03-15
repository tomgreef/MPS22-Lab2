package queue;

public class DoubleLinkedListQueue implements DoubleEndedQueue{
    private DequeNode next ;
    private DequeNode previous ;

    @Override
    public void append(DequeNode node) {
    }

    @Override
    public void appendLeft(DequeNode node) {

    }

    @Override
    public void deleteFirst() {

    }

    @Override
    public void deleteLast() {

    }

    @Override
    public DequeNode peekFirst() {
        return null;
    }

    @Override
    public DequeNode peekLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
