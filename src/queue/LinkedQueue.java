package queue;

// --> - path of some length >= 0 with Node.next
// INV: front --> back
public class LinkedQueue extends AbstractQueue {

  private Node front = null;
  private Node back = null;

  @Override
  protected void enqueueImpl(Object el) {
    if (back == null) {
      front = back = new Node(el, null);
    } else {
      back.next = new Node(el, null);
      back = back.next;
    }
  }

  @Override
  protected Object dequeueImpl() {
    Object res = front.value;
    front = front.next;
    if (front == null) {
      back = null;
    }
    return res;
  }

  @Override
  protected Object elementImpl() {
    return front.value;
  }

  @Override
  protected void clearImpl() {
    front = back = null;
  }

  @Override
  public LinkedQueue copyQueue() {
    LinkedQueue res = new LinkedQueue();
    Node it = front;
    while (it != null) {
      res.enqueue(it.value);
      it = it.next;
    }
    return res;
  }

  private class Node {
    private Object value;
    private Node next;

    public Node(Object value, Node next) {
      assert value != null;
      this.value = value;
      this.next = next;
    }
  }

}