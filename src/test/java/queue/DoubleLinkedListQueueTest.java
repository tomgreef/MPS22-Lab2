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
        doubleLinkedListQueue.deleteTail();
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteHead();
        assertEquals(0, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Peek first item")
    public void testPeekFirstItem() {
        DequeNode<String> dequeNodeFirst = null, dequeNodeLast = null;
        dequeNodeFirst = new DequeNode<String>("First", dequeNodeLast, null);
        dequeNodeLast = new DequeNode<String>("Last", null, dequeNodeFirst);

        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.peekHead());
    }

    @Test
    @DisplayName("Peek last item")
    public void testPeekLasttItem() {
        DequeNode<String> dequeNodeFirst = null, dequeNodeLast = null;
        dequeNodeFirst = new DequeNode<String>("First", dequeNodeLast, null);
        dequeNodeLast = new DequeNode<String>("Last", null, dequeNodeFirst);

        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(dequeNodeLast, doubleLinkedListQueue.peekTail());
    }

    @Test
    @DisplayName("Throws exception on deleting in empty Queue")
    public void testThrowsRuntimeExceptionIfEmpty() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteHead());
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteTail());
    }

    @Test
    @DisplayName("Throws exception on appending null node")
    public void testThrowsRuntimeExceptionIfAppendingNull() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.append(null));
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.appendLeft(null));
    }

    @Test
    @DisplayName("Return null on peeking in empty Queue")
    public void testReturnsNullIfPeekingInEmptyQueue() {
        assertNull(doubleLinkedListQueue.peekTail());
        assertNull(doubleLinkedListQueue.peekHead());
    }

    @Test
    @DisplayName("Is Empty return true on empty")
    public void testReturnsTrueIfEmpty() {
        DequeNode<String> dequeNode = null;
        dequeNode = new DequeNode<String>("Start", new DequeNode<>("End", null, dequeNode), null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.append(dequeNode);
        assertFalse(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.deleteTail();
        assertTrue(doubleLinkedListQueue.isEmpty());
    }

    @Test
    @DisplayName("Constructor creates a DoubleLinkedListQueue")
    public void testCreatesADoubleLinkedListQueue() {
        assertEquals(0, doubleLinkedListQueue.size());
        assertNull(doubleLinkedListQueue.peekTail());
        assertNull(doubleLinkedListQueue.peekHead());
        assertNotNull(doubleLinkedListQueue = new DoubleLinkedListQueue());
    }

    @Test
    @DisplayName("Appends adds 3 DequeNodes to the queue")
    public void testAppends3DequeNodes() {
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.append(dequeNodeFirst);
        assertEquals(1, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNodeFirst);
        assertNotNull(doubleLinkedListQueue.peekTail()); // If 1 node, then first = last = node
        doubleLinkedListQueue.append(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekTail(), dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(3, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekTail(), dequeNodeLast);
    }

    @Test
    @DisplayName("LeftAppend adds 3 DequeNodes to the queue")
    public void testLeftAppends3DequeNodes() {
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        assertEquals(1, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNodeLast);
        assertNotNull(doubleLinkedListQueue.peekTail()); // If 1 node, then first = last = node
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNode);
        assertSame(doubleLinkedListQueue.peekTail(), dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(3, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekHead(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekTail(), dequeNodeLast);
    }
}
