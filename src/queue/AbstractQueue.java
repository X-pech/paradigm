package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
  protected int size = 0;

  protected abstract void enqueueImpl(Object el);

  public void enqueue(Object el) {
    assert el != null;
    enqueueImpl(el);
    size++;
  }

  protected abstract Object elementImpl();

  public Object element() {
    assert size > 0;
    return elementImpl();
  }

  protected abstract Object dequeueImpl();

  public Object dequeue() {
    assert size > 0;
    size--;
    return dequeueImpl();
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  protected abstract void clearImpl();

  public void clear() {
    size = 0;
    clearImpl();
  }

  public abstract Queue copyQueue();

  public Queue filter(Predicate<Object> p) {
    Queue q = copyQueue();
    int old_s = q.size();
    for (int i = 0; i < old_s; i++) {
      Object o = q.dequeue();
      if (p.test(o)) {
        q.enqueue(o);
      }
    }
    return q;
  }

  public Queue map(Function<Object, Object> f) {
    Queue q = copyQueue();
    int old_s = q.size();
    for (int i = 0; i < old_s; i++) {
      Object o = q.dequeue();
      q.enqueue(f.apply(o));
    }
    return q;
  }

}
