package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

  @Test
  void copyQueue() {
    LinkedQueue lq = new LinkedQueue();
    lq.enqueue(1);
    LinkedQueue copy = lq.copyQueue();
    assertEquals(1, copy.element());
    assertEquals(1, copy.dequeue());
  }
}