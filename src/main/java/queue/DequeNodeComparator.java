package queue;

import java.util.Comparator;

public class DequeNodeComparator<T extends Comparable<T>> implements Comparator<DequeNode<T>> {
    public enum SortingOrder {ASC, DESC}

    private final SortingOrder sortingOrder;

    DequeNodeComparator(SortingOrder sort) {
        sortingOrder = sort;
    }

    @Override
    public int compare(DequeNode<T> o1, DequeNode<T> o2) {
        if (sortingOrder == SortingOrder.ASC)
            return o1.getItem().compareTo(o2.getItem());
        else
            return o2.getItem().compareTo(o1.getItem());
    }
}
