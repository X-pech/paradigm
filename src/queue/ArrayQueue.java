package queue;

// capacity - data.length
// INV: START_CAPACITY > 0 ^ capacity > 0 ^ 0 <= front < capacity
// INV: size < capacity
public class ArrayQueue extends AbstractQueue {
  private static int START_CAPACITY = 10;
  private Object data[];
  private int front;

  public ArrayQueue() {
    data = new Object[START_CAPACITY];
    front = size = 0;
  }

  // PRE: 0 <= x <= capacity - 1
  private int inc(int x, int a) {
    return (x + a) % data.length;
  }
  // POST: if 0 <= x < capacity - 1, R = x + 1
  //       if x = capacity - 1, R = 0

  // PRE: new_capacity > 0
  private void ensureCapacity(int new_capacity) {
    if (new_capacity >= data.length) {
      Object[] new_data = new Object[new_capacity * 2];
      int back = inc(front, size);
      if (front <= back) {
        System.arraycopy(data, front, new_data, 0, size);
      } else {
        System.arraycopy(data, front, new_data, 0, data.length - front);
        System.arraycopy(data, 0, new_data, data.length - front, back);
      }
      front = 0;
      data = new_data;
    }
  }
  // POST: capacity' >= new_capacity ^ a' = a

  @Override
  protected void enqueueImpl(Object el) {
    ensureCapacity(size + 1);
    data[inc(front, size)] = el;
  }

  @Override
  protected Object elementImpl() {
    return data[front];
  }

  @Override
  protected Object dequeueImpl() {
    Object res = data[front];
    data[front] = null;
    front = inc(front, 1);
    return res;
  }

  @Override
  public ArrayQueue copyQueue() {
    ArrayQueue res = new ArrayQueue();
    res.data = new Object[data.length];
    System.arraycopy(data, 0, res.data, 0, data.length);
    res.front = front;
    res.size = size;
    return res;
  }

  @Override
  protected void clearImpl() {
    data = new Object[START_CAPACITY];
    front = 0;
  }

}
