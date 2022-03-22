package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sort.StringComparator;

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
    @DisplayName("Delete decreases size (first we delete the last node later the first node)")
    public void testDeleteDecreasesSizeLastElementFirstElement() {
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
    @DisplayName("Delete decreases size (first we delete the first node later the last node)")
    public void testDeleteDecreasesSizeFirstElementLastElement() {
        DequeNode<String> dequeNode = null;
        dequeNode = new DequeNode<String>("Start", new DequeNode<>("End", null, dequeNode), null);

        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNode);
        assertEquals(2, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteHead();
        assertEquals(1, doubleLinkedListQueue.size());
        doubleLinkedListQueue.deleteTail();
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
    public void testPeekLastItem() {
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

    //Complex Methods ------------------

    @Test
    @DisplayName("Getting an item when the size is 0 returns null")
    public void gettingItemInEmptyQueueReturnsNull(){
        assertNull(doubleLinkedListQueue.getAt(0));
    }

    @Test
    @DisplayName("getting an item with the position bigger than the size when the size is 3 returns null")
    public void gettingTheItemFromQueueReturnsNullWithOutOfBoundPosition(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(null,doubleLinkedListQueue.getAt(3));
    }

    @Test
    @DisplayName("Getting an item with the a concrete position returns the same item")
    public void gettingTheItemFromQueueReturnsWithAPos(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeLast);
        assertEquals(dequeNodeFirst,doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNode,doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeLast,doubleLinkedListQueue.getAt(2));
    }

    @Test
    @DisplayName("finding an element when the queue is null")
    public void findingAnElementWithEmptyQueue(){
        assertEquals(null, doubleLinkedListQueue.find(new DequeNode<String>("test",null,null)));
    }

    @Test
    @DisplayName("finding an element when its not included in the queue")
    public void findingNonExistentElement(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.appendLeft(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        assertNull(doubleLinkedListQueue.find(new DequeNode<String>("test",null,null)));
     }

    @Test
    @DisplayName("Finding all the elements of the queue")
    public void findingAllTheElementsOfTheQueue(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeFirst);
        assertEquals(dequeNodeFirst,doubleLinkedListQueue.find(dequeNodeFirst));
        assertEquals(dequeNode,doubleLinkedListQueue.find(dequeNode));
        assertEquals(dequeNodeLast,doubleLinkedListQueue.find(dequeNodeLast));
    }

    @Test
    @DisplayName("deleting an element from an empty queue")
    public void deleteElementEmptyQueue(){
        assertThrows(RuntimeException.class, () -> doubleLinkedListQueue.delete(new DequeNode("Test",null,null)));
    }

    @Test
    @DisplayName("deleting a non existent element from queue")
    public void deleteNonExistentElementQueue(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeLast);
        doubleLinkedListQueue.append(dequeNode);
        doubleLinkedListQueue.append(dequeNodeFirst);
        assertThrows(RuntimeException.class, () ->doubleLinkedListQueue.delete(new DequeNode<>("test",null,null)));
   }

    @Test
    @DisplayName("Delete an element from queue")
    public void deleteAmElementFromAQueue(){
        DequeNode dequeNode, dequeNodeFirst, dequeNodeLast;
        dequeNode = new DequeNode<String>("Middle", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.appendLeft(dequeNodeLast);
        doubleLinkedListQueue.appendLeft(dequeNode);
        doubleLinkedListQueue.appendLeft(dequeNodeFirst);
        doubleLinkedListQueue.delete(dequeNode);
        assertNull(doubleLinkedListQueue.find(dequeNode));
        doubleLinkedListQueue.delete(dequeNodeFirst);
        assertNull(doubleLinkedListQueue.find(dequeNodeFirst));
        doubleLinkedListQueue.delete(dequeNodeLast);
        assertNull(doubleLinkedListQueue.find(dequeNodeLast));
    }

    @Test
    @DisplayName("delete an element from queue reduces the size")
    public void deleteAmElementFromQueueReduceSize(){
        DequeNode dequeNodeFirst, dequeNodeLast;
        DequeNode dequeNodeSecond = new DequeNode<String>("Second", null, null);
        DequeNode dequeNodeThird = new DequeNode<String>("Third", null, null);
        DequeNode dequeNodeForth = new DequeNode<String>("Forth", null, null);
        dequeNodeFirst = new DequeNode<String>("First", null, null);
        dequeNodeLast = new DequeNode<String>("Last", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeSecond);
        doubleLinkedListQueue.append(dequeNodeThird);
        doubleLinkedListQueue.append(dequeNodeForth);
        doubleLinkedListQueue.append(dequeNodeLast);
        //now the size should reduce
        assertEquals(5,doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeFirst);
        assertEquals(4,doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeSecond);
        assertEquals(3,doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeThird);
        assertEquals(2,doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeForth);
        assertEquals(1,doubleLinkedListQueue.size());
        doubleLinkedListQueue.delete(dequeNodeLast);
        assertEquals(0,doubleLinkedListQueue.size());

    }



    @Test
    @DisplayName("sorting a queue")
    public void sortingAQueue(){
        DequeNode dequeNodeFirst, dequeNodeLast;
        DequeNode dequeNodeSecond = new DequeNode<String>("D", null, null);
        DequeNode dequeNodeThird = new DequeNode<String>("C", null, null);
        DequeNode dequeNodeForth = new DequeNode<String>("B", null, null);
        dequeNodeFirst = new DequeNode<String>("E", null, null);
        dequeNodeLast = new DequeNode<String>("A", null, null);
        doubleLinkedListQueue.append(dequeNodeFirst);
        doubleLinkedListQueue.append(dequeNodeSecond);
        doubleLinkedListQueue.append(dequeNodeThird);
        doubleLinkedListQueue.append(dequeNodeForth);
        doubleLinkedListQueue.append(dequeNodeLast);
        //Before sorting the queue
        assertEquals(dequeNodeFirst,doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNodeSecond,doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeThird,doubleLinkedListQueue.getAt(2));
        assertEquals(dequeNodeForth,doubleLinkedListQueue.getAt(3));
        assertEquals(dequeNodeLast,doubleLinkedListQueue.getAt(4));
        //After sorting
        doubleLinkedListQueue.sort(new StringComparator());
        assertEquals(dequeNodeLast,doubleLinkedListQueue.getAt(0));
        assertEquals(dequeNodeForth,doubleLinkedListQueue.getAt(1));
        assertEquals(dequeNodeThird,doubleLinkedListQueue.getAt(2));
        assertEquals(dequeNodeSecond,doubleLinkedListQueue.getAt(3));
        assertEquals(dequeNodeFirst,doubleLinkedListQueue.getAt(4));

    }






}
