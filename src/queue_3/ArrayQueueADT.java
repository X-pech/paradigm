package queue_3;

// a - queue in iteration order 0..size - 1
// capacity - data.length
// ALL VARIABLE REFERENCES ARE RELATE TO queueADT argument's fields!
// INV: START_CAPACITY > 0 ^ size >= 0 ^ capacity > 0 ^ 0 <= front < capacity ^ 0 <= back < capacity
// INV: size < capacity
// INV: (front < back ^ size = back - front) | (front > back ^ size = back + capacity - front)
public class ArrayQueueADT {
  private static int START_CAPACITY = 10;
  private Object data[];
  private int front;
  private int back;

  public ArrayQueueADT() {
    data = new Object[START_CAPACITY];
    front = back = 0;
  }

  // PRE: queueADT != null ^ 0 <= x <= capacity - 1
  private static int inc(ArrayQueueADT queueADT, int x) {
    return (x + 1) % queueADT.data.length;
  }
  // POST: if 0 <= x < capacity - 1, R = x + 1
  //       if x = capacity - 1, R = 0

  // PRE: queueADT != null ^ 0 <= x <= capacity - 1
  private static int dec(ArrayQueueADT queueADT, int x) {
    return (x - 1 + queueADT.data.length) % queueADT.data.length;
  }
  // POST: if 0 < x <= capacity - 1, R = x - 1
  //       if x = 0, R = capacity - 1

  // PRE: queueADT != null ^ new_capacity > 0
  private static void ensureCapacity(ArrayQueueADT queueADT, int new_capacity) {
    if (new_capacity >= queueADT.data.length) {
      Object[] new_data = new Object[new_capacity * 2];
      if (queueADT.front <= queueADT.back) {
        System.arraycopy(queueADT.data, queueADT.front, new_data, 0, queueADT.back - queueADT.front);
      } else {
        System.arraycopy(queueADT.data, queueADT.front, new_data, 0, queueADT.data.length - queueADT.front);
        System.arraycopy(queueADT.data, 0, new_data, queueADT.data.length - queueADT.front, queueADT.back);
      }
      queueADT.back = size(queueADT);
      queueADT.front = 0;
      queueADT.data = new_data;
    }
  }
  // POST: capacity' >= new_capacity ^ a' = a

  // PRE: queueADT != null ^ el != null
  public static void enqueue(ArrayQueueADT queueADT, Object el) {
    assert el != null;
    ensureCapacity(queueADT, size(queueADT) + 1);
    queueADT.data[queueADT.back] = el;
    queueADT.back = inc(queueADT, queueADT.back);
  }
  // POST: size' = size + 1 ^ forall i = 0..size - 1 a'[i] = a[i] ^ a'[size] = el

  // PRE: queueADT != null ^ el != null
  public static void push(ArrayQueueADT queueADT, Object el) {
    assert el != null;
    ensureCapacity(queueADT, size(queueADT) + 1);
    queueADT.front = dec(queueADT, queueADT.front);
    queueADT.data[queueADT.front] = el;
  }
  // POST: size' = size + 1 ^ forall i = 0..size - 1 a'[i + 1] = a[i] ^ a[0] = el

  // PRE: queueADT != null ^ size > 0
  public static Object element(ArrayQueueADT queueADT) {
    assert size(queueADT) > 0;
    return queueADT.data[queueADT.front];
  }
  // POST: immutable ^ R = a[0]

  // PRE: queueADT != null ^ size > 0
  public static Object peek(ArrayQueueADT queueADT) {
    assert size(queueADT) > 0;
    return queueADT.data[dec(queueADT, queueADT.back)];
  }
  // POST: immutable ^ R = a[size - 1]

  // PRE: queueADT != null ^ size > 0
  public static Object dequeue(ArrayQueueADT queueADT) {
    assert size(queueADT) > 0;
    Object res = queueADT.data[queueADT.front];
    queueADT.data[queueADT.front] = null;
    queueADT.front = inc(queueADT, queueADT.front);
    return res;
  }
  // size' = size - 1 ^ forall i = 0..size' - 1 a'[i] = a[i + 1] ^ R = a[0]

  // PRE: queueADT != null ^ size > 0
  public static Object remove(ArrayQueueADT queueADT) {
    assert size(queueADT) > 0;
    int pb = dec(queueADT, queueADT.back);
    Object res = queueADT.data[pb];
    queueADT.data[pb] = null;
    queueADT.back = pb;
    return res;
  }
  // size' = size - 1 ^ forall i = 0..size' - 1 a'[i + 1] = a[i] ^ R = a[size']

  // queueADT != null
  public static int size(ArrayQueueADT queueADT) {
    return (queueADT.front <= queueADT.back ? queueADT.back - queueADT.front : queueADT.back + queueADT.data.length - queueADT.front);
  }
  // POST: immutable ^ R = size

  //PRE: queueADT != null
  public static boolean isEmpty(ArrayQueueADT queueADT) {
    return (size(queueADT) == 0);
  }
  // POST: immutable ^ ((size = 0 ^ R = true) || (size > 0 ^ R = false))

  // PRE: queueADT != null
  public static void clear(ArrayQueueADT queueADT) {
    queueADT.data = new Object[START_CAPACITY];
    queueADT.front = queueADT.back = 0;
  }
  // POST: capacity' = START_CAPACITY ^ size = 0

  public static int getStartCapacity() {
    return START_CAPACITY;
  }
  // POST: immutable

}
