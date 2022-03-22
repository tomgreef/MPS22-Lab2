package sort;

import queue.DequeNode;

import java.util.Comparator;

public class DoubleLinkedListQueueComparator<T> implements Comparator<T> {
    @Override
    public int compare(Object o1, Object o2) {
        if (DequeNode.class == o1.getClass() && DequeNode.class == o2.getClass())
            //return (((DequeNode<?>) o1).getItem()).equals(((DequeNode<?>) o2).getItem());
            return 1;
        else
            return 0;
    }
}
