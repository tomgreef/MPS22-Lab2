package queue;

import java.util.Comparator;

public interface DoubleEndedQueue<T> {
    // Basic operations
    void append(DequeNode<T> node);

    void appendLeft(DequeNode<T> node);

    void deleteHead();

    void deleteTail();

    DequeNode<T> peekHead();

    DequeNode<T> peekTail();

    int size();

    // Complex operations
    DequeNode<T> getAt(int position);

    DequeNode<T> find(T item);

    void delete(DequeNode<T> node);

    void sort(Comparator<?> comparator);
}