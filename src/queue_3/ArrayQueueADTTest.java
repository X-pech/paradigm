package queue_3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueADTTest {
  @Test
  void enqueue() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    Object last = null;
    for (int i = 0; i < ArrayQueueADT.getStartCapacity() + 2; i++) {
      try {
        ArrayQueueADT.enqueue(queueADT, i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueueADT.getStartCapacity() + 2, ArrayQueueADT.size(queueADT));
    assertEquals(last, ArrayQueueADT.peek(queueADT));
  }

  @Test
  void push() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    Object last = null;
    for (int i = 0; i < ArrayQueueADT.getStartCapacity() + 2; i++) {
      try {
        ArrayQueueADT.push(queueADT, i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueueADT.getStartCapacity() + 2, ArrayQueueADT.size(queueADT));
    assertEquals(last, ArrayQueueADT.element(queueADT));
  }

  @Test
  void element() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    ArrayQueueADT.enqueue(queueADT,25);
    ArrayQueueADT.enqueue(queueADT, 45);
    assertEquals(25, (int) ArrayQueueADT.element(queueADT));
  }

  @Test
  void peek() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    ArrayQueueADT.enqueue(queueADT,25);
    ArrayQueueADT.enqueue(queueADT,45);
    assertEquals(45, (int) ArrayQueueADT.peek(queueADT));
  }

  @Test
  void dequeue() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    ArrayQueueADT.enqueue(queueADT, 25);
    ArrayQueueADT.dequeue(queueADT);
    assertEquals(0, ArrayQueueADT.size(queueADT));
  }

  @Test
  void remove() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    ArrayQueueADT.enqueue(queueADT,25);
    ArrayQueueADT.remove(queueADT);
    assertEquals(0, ArrayQueueADT.size(queueADT));
  }

  @Test
  void size() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    int s = (int)(Math.random() * 100);
    for (int i = 0; i < s; i++) {
      ArrayQueueADT.enqueue(queueADT, i);
    }
    assertEquals(s, ArrayQueueADT.size(queueADT));
  }

  @Test
  void isEmpty() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    assertEquals(true, ArrayQueueADT.isEmpty(queueADT));
    ArrayQueueADT.enqueue(queueADT, 25);
    assertEquals(false, ArrayQueueADT.isEmpty(queueADT));
    ArrayQueueADT.dequeue(queueADT);
    assertEquals(true, ArrayQueueADT.isEmpty(queueADT));
  }

  @Test
  void clear() {
    ArrayQueueADT queueADT = new ArrayQueueADT();
    int s = (int)(Math.random() * 100);
    for (int i = 0; i < s; i++) {
      ArrayQueueADT.enqueue(queueADT, i);
    }
    ArrayQueueADT.clear(queueADT);
    assertEquals(true, ArrayQueueADT.isEmpty(queueADT));
  }
}