package queue;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DequeNodeTest<T> {
    private DequeNode<String> dequeNode;
    private DequeNode<String> dequeNodeNext;
    private DequeNode<String> dequeNodePrevious;

    @AfterEach
    public void tearDown() {
        dequeNode = null;
        dequeNodeNext = null ;
        dequeNodePrevious = null ;
    }

    @Test
    @DisplayName("Setting an item")
    public void setNodeItem(){
        dequeNode = new DequeNode<>("Start",null,null);
        dequeNode.setItem("Changed");
        assertEquals("Changed",dequeNode.getItem());
    }


    @Test
    @DisplayName("The list only contains one element")
    public void listSizeIsOne() {
        dequeNode = new DequeNode<>("Start", null, null);
        assertAll(
                "heading",
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertEquals(null, dequeNode.getNext(), "No elements returns null"),
                () -> assertEquals(null, dequeNode.getPrevious(), "No elements returns null"),
                () -> assertEquals(true, dequeNode.isHeadNode(), "Returns True"),
                () -> assertEquals(true, dequeNode.isTailNode(), "Returns True"),
                () -> assertEquals(false, dequeNode.isNotATerminalNode(), "Returns False")

        );
    }

    @Test
    @DisplayName("The list contains two elements")
    public void listSizeIsTwo() {
        dequeNodeNext = new DequeNode<>("End", null, dequeNode);
        dequeNode = new DequeNode<>("Start", dequeNodeNext, null);
        dequeNodeNext.setPrevious(dequeNode);

        assertAll(
                "heading",
                // first Element
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNodeNext, dequeNode.getNext(), "Returns the last value"),
                () -> assertEquals(null, dequeNode.getPrevious(), "No elements returns null"),
                () -> assertEquals(true, dequeNode.isHeadNode(), "Returns True"),
                () -> assertEquals(false, dequeNode.isTailNode(), "Returns False"),
                () -> assertEquals(false, dequeNode.isNotATerminalNode(), "Returns False"),
//                 last Element
                () -> assertEquals("End", dequeNodeNext.getItem(), "It returns the same value"),
                () -> assertEquals(null, dequeNodeNext.getNext(), "No element returns null"),
                () -> assertEquals(dequeNode, dequeNodeNext.getPrevious(), "Returns dequeNode as the previous value"),
                () -> assertEquals(false, dequeNodeNext.isHeadNode(), "Returns False"),
                () -> assertEquals(true, dequeNodeNext.isTailNode(), "Returns True"),
                () -> assertEquals(false, dequeNodeNext.isNotATerminalNode(), "Returns False")

        );
    }

    @Test
    @DisplayName("The list contains three elements")
    public void listSizeIsThree() {
        dequeNodeNext = new DequeNode<>("End", null, dequeNode);
        dequeNode = new DequeNode<>("Start", dequeNodeNext, dequeNodePrevious);
        dequeNodePrevious = new DequeNode<>("First",dequeNode,null);
        dequeNodeNext.setPrevious(dequeNode);
        dequeNodePrevious.setNext(dequeNode);
        dequeNode.setNext(dequeNodeNext);
        dequeNode.setPrevious(dequeNodePrevious);

        assertAll(
                "heading",
                //first Element
                () -> assertEquals("First", dequeNodePrevious.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNode, dequeNodePrevious.getNext(), "Returns the middle element"),
                () -> assertEquals(null, dequeNodePrevious.getPrevious(), "No element returns null"),
                () -> assertEquals(true, dequeNodePrevious.isHeadNode(), "Returns True"),
                () -> assertEquals(false, dequeNodePrevious.isTailNode(), "Returns False"),
                () -> assertEquals(false, dequeNodePrevious.isNotATerminalNode(), "Returns False"),
                // middle Element
                () -> assertEquals("Start", dequeNode.getItem(), "It returns the same value"),
                () -> assertEquals(dequeNodeNext, dequeNode.getNext(), "Returns the last value"),
                () -> assertEquals(dequeNodePrevious, dequeNode.getPrevious(), "Returns the first value"),
                () -> assertEquals(false, dequeNode.isHeadNode(), "Returns False"),
                () -> assertEquals(false, dequeNode.isTailNode(), "Returns False"),
                () -> assertEquals(true, dequeNode.isNotATerminalNode(), "Returns True"),
//                 last Element
                () -> assertEquals("End", dequeNodeNext.getItem(), "It returns the same value"),
                () -> assertEquals(null, dequeNodeNext.getNext(), "No element returns null"),
                () -> assertEquals(dequeNode, dequeNodeNext.getPrevious(), "Returns dequeNode as the previous value"),
                () -> assertEquals(false, dequeNodeNext.isHeadNode(), "Returns False"),
                () -> assertEquals(true, dequeNodeNext.isTailNode(), "Returns True"),
                () -> assertEquals(false, dequeNodeNext.isNotATerminalNode(), "Returns False")
        );
    }

}
