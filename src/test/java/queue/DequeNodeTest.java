package queue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DequeNodeTest<T> {
    private DequeNode<String> dequeNode;
    private DequeNode<String> dequeNodeNext;
    private DequeNode<String> dequeNodePrevious;

    @AfterEach
    void tearDown() {
        dequeNode = null;
        dequeNodeNext = null;
        dequeNodePrevious = null;
    }

    @Test
    @DisplayName("Setting an item")
    @ParameterizedTest
    @ValueSource(strings = { "test1", "test2", "test3" })
    @Tag("set")
    void setNodeItem() {
        dequeNode = new DequeNode<>(item, null, null);
        dequeNode.setItem(item);
        assertEquals(item, dequeNode.getItem());
    }

    @Test
    @DisplayName("The list only contains one element")
    @Tag("size = 1")
    void listSizeIsOne() {
        dequeNode = new DequeNode<>("Start", null, null);
        assertAll(
                "heading",
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertNull(dequeNode.getNext(), "No elements returns null"),
                () -> assertNull(dequeNode.getPrevious(), "No elements returns null"),
                () -> assertTrue(dequeNode.isHeadNode(), "Returns True"),
                () -> assertTrue(dequeNode.isTailNode(), "Returns True"),
                () -> assertFalse(dequeNode.isNotATerminalNode(), "Returns False")
        );
    }

    @Test
    @DisplayName("The list contains two elements")
    @Tag("size = 2")
    void listSizeIsTwo() {
        dequeNodeNext = new DequeNode<>("End", null, dequeNode);
        dequeNode = new DequeNode<>("Start", dequeNodeNext, null);
        dequeNodeNext.setPrevious(dequeNode);

        assertAll(
                "heading",
                // first Element
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNodeNext, dequeNode.getNext(), "Returns the last value"),
                () -> assertNull(dequeNode.getPrevious(), "No elements returns null"),
                () -> assertTrue(dequeNode.isHeadNode(), "Returns True"),
                () -> assertFalse(dequeNode.isTailNode(), "Returns False"),
                () -> assertFalse(dequeNode.isNotATerminalNode(), "Returns False"),
//                 last Element
                () -> assertEquals("End", dequeNodeNext.getItem(), "It returns the same value"),
                () -> assertNull(dequeNodeNext.getNext(), "No element returns null"),
                () -> assertEquals(dequeNode, dequeNodeNext.getPrevious(), "Returns dequeNode as the previous value"),
                () -> assertFalse(dequeNodeNext.isHeadNode(), "Returns False"),
                () -> assertTrue(dequeNodeNext.isTailNode(), "Returns True"),
                () -> assertFalse(dequeNodeNext.isNotATerminalNode(), "Returns False")
        );
    }

    @Test
    @DisplayName("The list contains three elements")
    @Tag("size = 3")
    void listSizeIsThree() {
        dequeNodeNext = new DequeNode<>("End", null, dequeNode);
        dequeNode = new DequeNode<>("Start", dequeNodeNext, dequeNodePrevious);
        dequeNodePrevious = new DequeNode<>("First", dequeNode, null);
        dequeNodeNext.setPrevious(dequeNode);
        dequeNodePrevious.setNext(dequeNode);
        dequeNode.setNext(dequeNodeNext);
        dequeNode.setPrevious(dequeNodePrevious);

        assertAll(
                "heading",
                //first Element
                () -> assertEquals("First", dequeNodePrevious.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNode, dequeNodePrevious.getNext(), "Returns the middle element"),
                () -> assertNull(dequeNodePrevious.getPrevious(), "No element returns null"),
                () -> assertTrue(dequeNodePrevious.isHeadNode(), "Returns True"),
                () -> assertFalse(dequeNodePrevious.isTailNode(), "Returns False"),
                () -> assertFalse(dequeNodePrevious.isNotATerminalNode(), "Returns False"),
                // middle Element
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNodeNext, dequeNode.getNext(), "Returns the last value"),
                () -> assertEquals(dequeNodePrevious, dequeNode.getPrevious(), "Returns the first value"),
                () -> assertFalse(dequeNode.isHeadNode(), "Returns False"),
                () -> assertFalse(dequeNode.isTailNode(), "Returns False"),
                () -> assertTrue(dequeNode.isNotATerminalNode(), "Returns True"),
//                 last Element
                () -> assertEquals("End", dequeNodeNext.getItem(), "It returns the same value"),
                () -> assertNull(dequeNodeNext.getNext(), "No element returns null"),
                () -> assertEquals(dequeNode, dequeNodeNext.getPrevious(), "Returns dequeNode as the previous value"),
                () -> assertFalse(dequeNodeNext.isHeadNode(), "Returns False"),
                () -> assertTrue(dequeNodeNext.isTailNode(), "Returns True"),
                () -> assertFalse(dequeNodeNext.isNotATerminalNode(), "Returns False")
        );
    }

    @Test
    @Tag("Exceptions")
    @DisplayName("Null as Item of the constructor's argument throws RuntimeException")
    void wePassNullAsArgument() {
        assertThrows(RuntimeException.class, () -> dequeNode = new DequeNode<>(null, null, null));
    }
}
