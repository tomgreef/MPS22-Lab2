package queue;

public class main {
    public static void main(String[] args) {
        DoubleLinkedListQueue doubleLinkedListQueue = new DoubleLinkedListQueue();
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.appendLeft(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
//        System.out.println(doubleLinkedListQueue.peekFirst().getItem());
//        System.out.println(doubleLinkedListQueue.peekFirst().getNext().getItem()); //here appears an error
//        System.out.println(doubleLinkedListQueue.peekFirst().getNext().getNext().getItem());
        System.out.println(doubleLinkedListQueue.peekFirst().getPrevious().getItem());
        System.out.println(doubleLinkedListQueue.peekFirst().getPrevious().getPrevious().getItem());


    }
}
