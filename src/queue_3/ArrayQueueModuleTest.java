package queue_3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueModuleTest {
  @Test
  void enqueue() {
    ArrayQueueModule.clear();
    Object last = null;
    for (int i = 0; i < ArrayQueueModule.getStartCapacity() + 2; i++) {
      try {
        ArrayQueueModule.enqueue(i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueueModule.getStartCapacity() + 2, ArrayQueueModule.size());
    assertEquals(last, ArrayQueueModule.peek());
  }

  @Test
  void push() {
    ArrayQueueModule.clear();
    Object last = null;
    for (int i = 0; i < ArrayQueueModule.getStartCapacity() + 2; i++) {
      try {
        ArrayQueueModule.push(i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueueModule.getStartCapacity() + 2, ArrayQueueModule.size());
    assertEquals(last, ArrayQueueModule.element());
  }

  @Test
  void element() {
    ArrayQueueModule.clear();
    ArrayQueueModule.enqueue(25);
    ArrayQueueModule.enqueue(45);
    assertEquals(25, (int) ArrayQueueModule.element());
  }

  @Test
  void peek() {
    ArrayQueueModule.clear();
    ArrayQueueModule.enqueue(25);
    ArrayQueueModule.enqueue(45);
    assertEquals(45, (int) ArrayQueueModule.peek());
  }

  @Test
  void dequeue() {
    ArrayQueueModule.clear();
    ArrayQueueModule.enqueue(25);
    ArrayQueueModule.dequeue();
    assertEquals(0, ArrayQueueModule.size());
  }

  @Test
  void remove() {
    ArrayQueueModule.clear();
    ArrayQueueModule.enqueue(25);
    ArrayQueueModule.remove();
    assertEquals(0, ArrayQueueModule.size());
  }

  @Test
  void size() {
    ArrayQueueModule.clear();
    int s = (int)(Math.random() * 100);
    for (int i = 0; i < s; i++) {
      ArrayQueueModule.enqueue(i);
    }
    assertEquals(s, ArrayQueueModule.size());
  }

  @Test
  void isEmpty() {
    ArrayQueueModule.clear();
    assertEquals(true, ArrayQueueModule.isEmpty());
    ArrayQueueModule.enqueue(25);
    assertEquals(false, ArrayQueueModule.isEmpty());
    ArrayQueueModule.dequeue();
    assertEquals(true, ArrayQueueModule.isEmpty());
  }

  @Test
  void clear() {
    ArrayQueueModule.clear();
    int s = (int)(Math.random() * 100);
    for (int i = 0; i < s; i++) {
      ArrayQueueModule.enqueue(i);
    }
    ArrayQueueModule.clear();
    assertEquals(true, ArrayQueueModule.isEmpty());
  }
}