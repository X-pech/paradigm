package queue_3;

// a - queue in iteration order 0..size - 1
// capacity - data.length
// INV: START_CAPACITY > 0 ^ size >= 0 ^ capacity > 0 ^ 0 <= front < capacity ^ 0 <= back < capacity
// INV: size < capacity
// INV: (front < back ^ size = back - front) | (front > back ^ size = back + capacity - front)
public class ArrayQueue {
  private static int START_CAPACITY = 10;
  private Object data[];
  private int front;
  private int back;

  public ArrayQueue() {
    data = new Object[START_CAPACITY];
    front = back = 0;
  }

  // PRE: 0 <= x <= capacity - 1
  private int inc(int x) {
    return (x + 1) % data.length;
  }
  // POST: if 0 <= x < capacity - 1, R = x + 1
  //       if x = capacity - 1, R = 0

  // PRE: 0 <= x <= capacity - 1
  private int dec(int x) {
    return (x - 1 + data.length) % data.length;
  }
  // POST: if 0 < x <= capacity - 1, R = x - 1
  //       if x = 0, R = capacity - 1

  // PRE: new_capacity > 0
  private void ensureCapacity(int new_capacity) {
    if (new_capacity >= data.length) {
      Object[] new_data = new Object[new_capacity * 2];
      if (front <= back) {
        System.arraycopy(data, front, new_data, 0, back - front);
      } else {
        System.arraycopy(data, front, new_data, 0, data.length - front);
        System.arraycopy(data, 0, new_data, data.length - front, back);
      }
      back = size();
      front = 0;
      data = new_data;
    }
  }
  // POST: capacity' >= new_capacity ^ a' = a

  // PRE: el != null
  public void enqueue(Object el) {
    assert el != null;
    ensureCapacity(size() + 1);
    data[back] = el;
    back = inc(back);
  }
  // POST: size' = size + 1 ^ forall i = 0..size - 1 a'[i] = a[i] ^ a'[size] = el

  // PRE: el != null
  public void push(Object el) {
    assert el != null;
    ensureCapacity(size() + 1);
    front = dec(front);
    data[front] = el;
  }
  // POST: size' = size + 1 ^ forall i = 0..size - 1 a'[i + 1] = a[i] ^ a[0] = el

  // PRE: size > 0
  public Object element() {
    assert size() > 0;
    return data[front];
  }
  // POST: immutable ^ R = a[0]

  // PRE: size > 0
  public Object peek() {
    assert size() > 0;
    return data[dec(back)];
  }
  // POST: immutable ^ R = a[size - 1]

  // PRE: size > 0
  public Object dequeue() {
    assert size() > 0;
    Object res = data[front];
    data[front] = null;
    front = inc(front);
    return res;
  }
  // size' = size - 1 ^ forall i = 0..size' - 1 a'[i] = a[i + 1] ^ R = a[0]

  // PRE: size > 0
  public Object remove() {
    assert size() > 0;
    int pb = dec(back);
    Object res = data[pb];
    data[pb] = null;
    back = pb;
    return res;
  }
  // size' = size - 1 ^ forall i = 0..size' - 1 a'[i + 1] = a[i] ^ R = a[size']

  public int size() {
    return (front <= back ? back - front : back + data.length - front);
  }
  // POST: immutable ^ R = size

  public boolean isEmpty() {
    return (size() == 0);
  }
  // POST: immutable ^ ((size = 0 ^ R = true) || (size > 0 ^ R = false))


  public void clear() {
    data = new Object[START_CAPACITY];
    front = back = 0;
  }
  // POST: capacity' = START_CAPACITY ^ size = 0

  public static int getStartCapacity() {
    return START_CAPACITY;
  }
  // POST: immutable

}
