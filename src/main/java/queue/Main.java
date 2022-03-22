package queue;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedListQueue<String> doubleLinkedListQueue = new DoubleLinkedListQueue<>();
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<>("1", null, null);
        dequeNodeFirst = new DequeNode<>("2", null, null);
        dequeNodeLast = new DequeNode<>("3", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNode);

        // System.out.println(doubleLinkedListQueue.peekFirst().getItem());
        // System.out.println(doubleLinkedListQueue.peekFirst().getNext().getItem()); //here appears an error
        // System.out.println(doubleLinkedListQueue.peekFirst().getNext().getNext().getItem());
        // System.out.println(doubleLinkedListQueue.peekFirst().getPrevious().getItem());
        // System.out.println(doubleLinkedListQueue.peekFirst().getPrevious().getPrevious().getItem());

        // Sorting test
        System.out.println(doubleLinkedListQueue.getAt(0).getItem());
        System.out.println(doubleLinkedListQueue.getAt(1).getItem());
        System.out.println(doubleLinkedListQueue.getAt(2).getItem());
        doubleLinkedListQueue.sort(new DequeNodeComparator<>(DequeNodeComparator.SortingOrder.ASC));
        System.out.println("-----------SORTING-----------");
        System.out.println(doubleLinkedListQueue.getAt(0).getItem());
        System.out.println(doubleLinkedListQueue.getAt(1).getItem());
        System.out.println(doubleLinkedListQueue.getAt(2).getItem());
    }
}
