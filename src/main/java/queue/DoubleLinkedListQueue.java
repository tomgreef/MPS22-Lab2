package queue;

import sort.StringComparator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
            node.setNext(null);
            node.setPrevious(tail);
            tail.setNext(node);
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
            node.setPrevious(null);
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (isEmpty())
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
        if (isEmpty())
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
        if (isEmpty())
            return null;

        return head;
    }

    @Override
    public DequeNode peekLast() {
        if (isEmpty())
            return null;

        return tail;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public DequeNode getAt(int position) {
        DequeNode node = head;
        int counter = 0;

        while (!node.isLastNode())
            if (counter == position)
                return node;
            else {
                node = node.getNext();
                counter++;
            }

        return null;
    }

    @Override
    public DequeNode find(Object item) {
        DequeNode node = head;

        while (!node.isLastNode())
            if (node.equals(item))
                return node;
            else
                node = node.getNext();

        return null;
    }

    @Override
    public void delete(DequeNode node) {
        DequeNode aux = head;
        boolean found = false;

        while (!aux.isLastNode() && !found)
            if (aux.equals(node)) {
                if(aux == head){
                    deleteFirst();
                } else {
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                }
                found = true;
                --size;
            } else
                aux = aux.getNext();

        if (!found)
            throw new RuntimeException("Node: " + node + " not found");
    }

    @Override
    public void sort(Comparator comparator) {
        List<DequeNode> list = new LinkedList<DequeNode>();
        DequeNode aux = head;

        while(aux.getNext() != null){
            list.add(aux);
            aux = aux.getNext();
        }

        list.sort(new StringComparator());
        head = list.get(0);
        head.setPrevious(null);
        list.remove(head);
        aux = head;

        for (DequeNode node : list){
            aux.setNext(node);
            node.setPrevious(aux);
            aux = node;
        }

        tail = list.get(list.size()-1);
        tail.setNext(null);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
