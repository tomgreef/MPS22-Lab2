package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListQueueTest {
    private DoubleLinkedListQueue<String> doubleLinkedListQueue;

    @BeforeEach
    void setUp() {
        doubleLinkedListQueue = new DoubleLinkedListQueue<>();
    }

    @AfterEach
    void tearDown() {
        doubleLinkedListQueue = null;
    }

    @Test
    @DisplayName("Append increases size")
    void testAppendIncreasesSize() {
        DequeNode<String> dequeNode = new DequeNode<>("Start", null, null);

        doubleLinkedListQueue.append(dequeNode);
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Appends adds 3 DequeNodes to the queue")
    void testAppends3DequeNodes() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.append(dequeNodeFirst);
        assertEquals(1, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNodeFirst);
        assertNotNull(doubleLinkedListQueue.peekLast()); // If 1 node, then first = last = node
        doubleLinkedListQueue.append(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekLast(), dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(3, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekLast(), dequeNodeLast);
    }

    @Test
    @DisplayName("LeftAppend adds 3 DequeNodes to the queue")
    void testLeftAppends3DequeNodes() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        assertEquals(1, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNodeLast);
        assertNotNull(doubleLinkedListQueue.peekLast()); // If 1 node, then first = last = node
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNode);
        assertSame(doubleLinkedListQueue.peekLast(), dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(3, doubleLinkedListQueue.size());
        assertSame(doubleLinkedListQueue.peekFirst(), dequeNodeFirst);
        assertSame(doubleLinkedListQueue.peekLast(), dequeNodeLast);
    }

    @Test
    @DisplayName("Throws exception on appending null node")
    void testThrowsRuntimeExceptionIfAppendingNull() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.append(null));
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.appendLeft(null));
    }

    @Test
    @DisplayName("Delete decreases size (first we delete the last node later the first node)")
    void testDeleteDecreasesSizeLastElementFirstElement() {
        DequeNode<String> dequeNode = new DequeNode<String>("Start", null, null);

        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteLast();
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteFirst();
        assertEquals(0, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Delete decreases size (first we delete the first node later the last node)")
    void testDeleteDecreasesSizeFirstElementLastElement() {
        DequeNode<String> dequeNode = new DequeNode<String>("Start", null, null);

        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteFirst();
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteLast();
        assertEquals(0, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Throws exception on deleting in empty Queue")
    void testThrowsRuntimeExceptionIfEmpty() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteFirst());
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteLast());
    }

    @Test
    @DisplayName("Peek first item")
    void testPeekFirstItem() {
        DequeNode<String> dequeNodeFirst, dequeNodeLast;
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.peekFirst());
    }

    @Test
    @DisplayName("Peek last item")
    void testPeekLastItem() {
        DequeNode<String> dequeNodeFirst, dequeNodeLast;
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);

        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(dequeNodeLast, doubleLinkedListQueue.peekLast());
    }


    @Test
    @DisplayName("Return null on peeking in empty Queue")
    void testReturnsNullIfPeekingInEmptyQueue() {
        assertNull(doubleLinkedListQueue.peekLast());
        assertNull(doubleLinkedListQueue.peekFirst());
    }

    @Test
    @DisplayName("Is Empty return true on empty")
    void testReturnsTrueIfEmpty() {
        DequeNode<String> dequeNode = new DequeNode<String>("Start", null, null);

        assertTrue(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.append(dequeNode);
        assertFalse(doubleLinkedListQueue.isEmpty());
        doubleLinkedListQueue.deleteLast();
        assertTrue(doubleLinkedListQueue.isEmpty());
    }

    @Test
    @DisplayName("Constructor creates a DoubleLinkedListQueue")
    void testCreatesADoubleLinkedListQueue() {
        assertEquals(0, doubleLinkedListQueue.size());
        assertNull(doubleLinkedListQueue.peekLast());
        assertNull(doubleLinkedListQueue.peekFirst());
        assertNotNull(doubleLinkedListQueue = new DoubleLinkedListQueue<>());
    }

    //Complex Methods ------------------

    @Test
    @DisplayName("Getting an item when the size is 0 returns null")
    void gettingItemInEmptyQueueReturnsNull() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.getAt(0));
    }

    @Test
    @DisplayName("getting an item with the position bigger than the size when the size is 3 returns null")
    void gettingTheItemFromQueueReturnsNullWithOutOfBoundPosition() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.getAt(3));
    }

    @Test
    @DisplayName("Getting an item with the a concrete position returns the same item")
    void gettingTheItemFromQueueReturnsWithAPos() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNode, doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeLast, doubleLinkedListQueue.getAt(2));
    }

    @Test
    @DisplayName("Finding an element when the queue is empty")
    void findingAnElementWithEmptyQueue(){
        assertNull(doubleLinkedListQueue.find("test"));
    }

    @Test
    @DisplayName("Finding an element when its not included in the queue")
    void findingNonExistentElement(){
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.appendLeft(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        assertNull(doubleLinkedListQueue.find("test"));
        assertNotNull(doubleLinkedListQueue.find("First"));
    }

    @Test
    @DisplayName("Finding all the elements of the queue")
    void findingAllTheElementsOfTheQueue() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeFirst);
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.find("First"));
        assertEquals(dequeNode, doubleLinkedListQueue.find("Middle"));
        assertEquals(dequeNodeLast, doubleLinkedListQueue.find("Last"));
    }

    @Test
    @DisplayName("Deleting an element from an empty queue")
    void deleteElementEmptyQueue() {
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.deleteFirst());
    }

    @Test
    @DisplayName("Deleting a non existent element from queue")
    void deleteNonExistentElementQueue() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.append(dequeNode);
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.delete(dequeNodeFirst));
    }

    @Test
    @DisplayName("Delete an element from queue")
    void deleteAmElementFromAQueue() {
        DequeNode<String> dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<>("Middle", null, null);
        dequeNodeFirst = new DequeNode<>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.delete(dequeNode);
        assertNull(doubleLinkedListQueue.find("Middle"));
        doubleLinkedListQueue.delete(dequeNodeFirst);
        assertNull(doubleLinkedListQueue.find("First"));
        doubleLinkedListQueue.delete(dequeNodeLast);
        assertNull(doubleLinkedListQueue.find("Last"));
    }

    @Test
    @DisplayName("delete an element from queue reduces the size")
    void deleteAmElementFromQueueReduceSize() {
        DequeNode<String> dequeNodeFirst, dequeNodeLast;
        DequeNode<String> dequeNodeSecond = new DequeNode<>("Second", null, null);
        DequeNode<String> dequeNodeThird = new DequeNode<>("Third", null, null);
        DequeNode<String> dequeNodeForth = new DequeNode<>("Forth", null, null);
        dequeNodeFirst = new DequeNode<>("First", null, null);
        dequeNodeLast = new DequeNode<>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeSecond);
        doubleLinkedListQueue.append(dequeNodeThird);
        doubleLinkedListQueue.append(dequeNodeForth);
        doubleLinkedListQueue.append(dequeNodeLast);
        //now the size should reduce
        assertEquals(5, doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeFirst);
        assertEquals(4, doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeSecond);
        assertEquals(3, doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeThird);
        assertEquals(2, doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeForth);
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeLast);
        assertEquals(0, doubleLinkedListQueue.size());
    }

    @Test
    @DisplayName("Sorting a queue")
    void sortingAQueue() {
        DequeNode<String> dequeNodeFirst, dequeNodeLast;
        DequeNode<String> dequeNodeSecond = new DequeNode<>("3", null, null);
        DequeNode<String> dequeNodeThird = new DequeNode<>("2", null, null);
        DequeNode<String> dequeNodeForth = new DequeNode<>("1", null, null);
        dequeNodeFirst = new DequeNode<>("4", null, null);
        dequeNodeLast = new DequeNode<>("0", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeSecond);
        doubleLinkedListQueue.append(dequeNodeThird);
        doubleLinkedListQueue.append(dequeNodeForth);
        doubleLinkedListQueue.append(dequeNodeLast);
        //Before sorting the queue
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNodeSecond, doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeThird, doubleLinkedListQueue.getAt(2));
        assertEquals(dequeNodeForth, doubleLinkedListQueue.getAt(3));
        assertEquals(dequeNodeLast, doubleLinkedListQueue.getAt(4));
        //After sorting
        doubleLinkedListQueue.sort(new DequeNodeComparator<>(DequeNodeComparator.SortingOrder.ASC));
        assertEquals(dequeNodeLast, doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNodeForth, doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeThird, doubleLinkedListQueue.getAt(2));
        assertEquals(dequeNodeSecond, doubleLinkedListQueue.getAt(3));
        assertEquals(dequeNodeFirst, doubleLinkedListQueue.getAt(4));
    }
}
