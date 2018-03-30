package queue_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayQueueTest {
  @Test
  void enqueue() {
    ArrayQueue arrayQueue = new ArrayQueue();
    Object last = null;
    for (int i = 0; i < ArrayQueue.getStartCapacity() + 2; i++) {
      try {
        arrayQueue.enqueue(i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueue.getStartCapacity() + 2, arrayQueue.size());
    assertEquals(last, arrayQueue.peek());
  }

  @Test
  void push() {
    ArrayQueue arrayQueue = new ArrayQueue();
    Object last = null;
    for (int i = 0; i < ArrayQueue.getStartCapacity() + 2; i++) {
      try {
        arrayQueue.push(i);
        last = i;
      } catch (Exception e) {
        System.out.println(e.toString());
        e.printStackTrace();
      }
    }
    assertEquals(ArrayQueue.getStartCapacity() + 2, arrayQueue.size());
    assertEquals(last, arrayQueue.element());
  }

  @Test
  void element() {
    ArrayQueue arrayQueue = new ArrayQueue();
    arrayQueue.enqueue(25);
    arrayQueue.enqueue(45);
    assertEquals(25, (int) arrayQueue.element());
  }

  @Test
  void peek() {
    ArrayQueue arrayQueue = new ArrayQueue();
    arrayQueue.enqueue(25);
    arrayQueue.enqueue(45);
    assertEquals(45, (int) arrayQueue.peek());
  }

  @Test
  void dequeue() {
    ArrayQueue arrayQueue = new ArrayQueue();
    arrayQueue.enqueue(25);
    arrayQueue.dequeue();
    assertEquals(0, arrayQueue.size());
  }

  @Test
  void remove() {
    ArrayQueue arrayQueue = new ArrayQueue();
    arrayQueue.enqueue(25);
    arrayQueue.remove();
    assertEquals(0, arrayQueue.size());
  }

  @Test
  void size() {
    ArrayQueue arrayQueue = new ArrayQueue();
    int s = (int) (Math.random() * 100);
    for (int i = 0; i < s; i++) {
      arrayQueue.enqueue(i);
    }
    assertEquals(s, arrayQueue.size());
  }

  @Test
  void isEmpty() {
    ArrayQueue arrayQueue = new ArrayQueue();
    assertEquals(true, arrayQueue.isEmpty());
    arrayQueue.enqueue(25);
    assertEquals(false, arrayQueue.isEmpty());
    arrayQueue.dequeue();
    assertEquals(true, arrayQueue.isEmpty());
  }

  @Test
  void clear() {
    ArrayQueue arrayQueue = new ArrayQueue();
    int s = (int) (Math.random() * 100);
    for (int i = 0; i < s; i++) {
      arrayQueue.enqueue(i);
    }
    arrayQueue.clear();
    assertEquals(true, arrayQueue.isEmpty());
  }
}