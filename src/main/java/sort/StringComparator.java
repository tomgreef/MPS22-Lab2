package sort;

import queue.DequeNode;

import java.util.Comparator;

public class StringComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (DequeNode.class == o1.getClass() && DequeNode.class == o2.getClass())
            return ((String) ((DequeNode<?>) o1).getItem()).compareTo((String) (((DequeNode<?>) o2).getItem()));
        return 0;
    }
}
