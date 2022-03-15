package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListQueueTest {
    private DoubleLinkedListQueue doubleLinkedListQueue;

    @BeforeEach
    public void setUp() {
        doubleLinkedListQueue = new DoubleLinkedListQueue();
    }

    @AfterEach
    public void tearDown() {
        doubleLinkedListQueue = null;
    }

    @Test
    @DisplayName("Append increases size")
    public void testAppendIncreasesSize() {
        DequeNode<String> dequeNode = null;
        dequeNode = new DequeNode<String>("Start", new DequeNode<>("End", null, dequeNode), null);

        doubleLinkedListQueue.append(dequeNode);
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Delete decreases size")
    public void testDeleteDecreasesSize() {
        DequeNode<String> dequeNode = null;
        dequeNode = new DequeNode<String>("Start", new DequeNode<>("End", null, dequeNode), null);

        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteLast();
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteFirst();
        assertEquals(0, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Peek first item")
    public void testPeekFirstItem() {
        DequeNode<String> dequeNodeFirst = null, dequeNodeLast = null;
        dequeNodeFirst = new DequeNode<String>("First", dequeNodeLast, null);
        dequeNodeLast = new DequeNode<String>("Last", null , dequeNodeFirst);
        
        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.peekFirst());
    }

    @Test
    @DisplayName("Peek last item")
    public void testPeekLasttItem() {
        DequeNode<String> dequeNodeFirst = null, dequeNodeLast = null;
        dequeNodeFirst = new DequeNode<String>("First", dequeNodeLast, null);
        dequeNodeLast = new DequeNode<String>("Last", null , dequeNodeFirst);

        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(dequeNodeLast, doubleLinkedListQueue.peekLast());
    }

    @Test
    @DisplayName("Throws exception on deleting in empty Queue")
    public void testThrowsRuntimeExceptionIfEmpty(){
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteFirst());
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteLast());
    }

    @Test
    @DisplayName("Throws exception on appending null node")
    public void testThrowsRuntimeExceptionIfAppendingNull(){
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.append(null));
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.appendLeft(null));
    }

    @Test
    @DisplayName("Throws exception on peeking in empty Queue")
    public void testThrowsRuntimeExceptionIfPeekingInEmptyQueue(){
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.peekFirst());
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.peekLast());
    }
}
