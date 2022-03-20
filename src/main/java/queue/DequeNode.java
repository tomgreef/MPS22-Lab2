package queue;

/**
 * Class representing a node of a double-ended queue (deque). Each node has pointers to
 * the next and previous nodes.
 * The previous and next of the first and last node of the deque is null.
 *
 * @param <T>
 * @author Tom & Parsa
 */
public class DequeNode<T> implements Comparable<DequeNode>{
    private T item;
    private DequeNode<T> next;
    private DequeNode<T> previous;

    // Setters
    public void setItem(T item) {
        this.item = item;
    }

    public void setNext(DequeNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DequeNode<T> previous) {
        this.previous = previous;
    }

    // Getters
    public T getItem() {
        return item;
    }

    public DequeNode<T> getNext() {
        return next;
    }

    public DequeNode<T> getPrevious() {
        return previous;
    }

    public DequeNode(T item, DequeNode<T> next, DequeNode<T> previous) {
        this.item = item;
        this.next = next;
        this.previous = previous;
        if(item==null) throw new RuntimeException("the element cannot be null");
        if(next==this || previous==this) throw new RuntimeException("the passed element cannot be the same");
    }

    public boolean isFirstNode() {
        return previous == null;
    }

    public boolean isLastNode() {
        return next == null;
    }

    public boolean isNotATerminalNode() {
        return (!isFirstNode() && !isLastNode());
    }

    @Override
    public int compareTo(DequeNode o) {
        return this.item.toString().compareTo(o.getItem().toString());
    }
}