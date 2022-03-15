package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DequeNodeTest<T> {
    private DequeNode<String> dequeNode;

    @AfterEach
    public void tearDown() {
        dequeNode = null;
    }

    /* Test with 2 items */
    @Test
    @DisplayName("Queue has next ")
    public void testDeque() {
        dequeNode = new DequeNode<>("Start", new DequeNode<>("End", null, dequeNode), null);
        assertTrue(dequeNode.isFirstNode());
        assertTrue(dequeNode.getNext().isLastNode());
    }
}
