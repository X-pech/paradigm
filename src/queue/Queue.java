package queue;

import java.util.function.Function;
import java.util.function.Predicate;

// INV: a - queue in iteration order 0..size
// INV: size >= 0
public interface Queue {

  // PRE: el != null
  void enqueue(Object el);
  // POST: size' = size + 1, forall i = 0..size - 1: a'[i] = a[i], a'[size] = el

  // PRE: size > 0
  Object element();
  // POST: immutable ^ R = a[0]

  // PRE: size > 0
  Object dequeue();
  // POST: size' = size - 1, forall i = 1..size - 1: a'[i - 1] = a[i], R = a[0]

  int size();
  // POST: immutable ^ R = size

  boolean isEmpty();
  // POST: immutable ^ ((size = 0 ^ R = true) | (size > 0 R = false))

  void clear();
  // POST: size = 0

  Queue copyQueue();
  // POST: immutable, R = this

  // PRE: p != null
  Queue filter(Predicate<Object> p);
  // POST: immutable, R.type = type, R.size <= size, R.a = a : p(a[i]) == true in the same order

  // PRE: f != null
  Queue map(Function<Object, Object> f);
  // POST: immutable, R.type = type, R.size = size, forall i = 0..size - 1 R.a[i] = f(a[i])

}
