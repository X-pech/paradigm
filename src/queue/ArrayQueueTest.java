package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

  @Test
  void copyQueue() {
    ArrayQueue q = new ArrayQueue();
    for (int i = 0; i < 11; i++) {
      q.enqueue(i);
    }
    ArrayQueue copy = q.copyQueue();
    assertEquals(0, copy.element());
  }
}