package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DequeNodeTest<T> {
    private DequeNode<T> dequeNode;

/*    @BeforeEach
    public void setup() {
        T value = null;

        dequeNode = new DequeNode<T>(value, new DequeNode<T>(value, dequeNode, null), new DequeNode<T>(value, null, dequeNode));
    }*/

    @AfterEach
    private void finish() {
        dequeNode = null;
    }

    @Test
    @DisplayName("Test if it is last node")
    public void testIfLastItemIsLastNode(){
        DequeNode<T> firstNode = null, secondNode = null, lastNode = null;

   /*     firstNode = new DequeNode<T>(null, secondNode, null);
        lastNode = new DequeNode<T>(null, null, secondNode);
        secondNode = new DequeNode<T>(null, lastNode, firstNode);*/

        assertTrue(lastNode.isLastNode());
        assertTrue(firstNode.isFirstNode());
        assertTrue(secondNode.isNotATerminalNode());
    }
}
