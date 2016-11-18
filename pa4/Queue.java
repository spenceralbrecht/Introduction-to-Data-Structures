class Queue implements QueueInterface {

  private class Node<T> {
    T job;
    Node<T> next;

    Node(T job) {
      this.job = job;
      this.next = null;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private int numItems;

  public Queue() {
    head = null;
    tail = null;
    numItems = 0;
  }

  // isEmpty()
  // pre: none
  // post: returns true if this Queue is empty, false otherwise
  public boolean isEmpty() {
    return (numItems==0);
  }

  // length()
  // pre: none
  // post: returns the length of this Queue.
  public int length() {
    return numItems;
  }

  // enqueue()
  // adds newItem to back of this Queue
  // pre: none
  // post: !isEmpty()
  public void enqueue(T newItem) {
    Node<T> insertNode = new Node<T>(newItem);
    tail.next = insertNode;
    tail = insertNode;
  }

  // dequeue()
  // deletes and returns item from front of this Queue
  // pre: !isEmpty()
  // post: this Queue will have one fewer element
  public T dequeue() throws QueueEmptyException {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: dequeue() called on empty queue");
    }
    Node<T> tempNode = head;
    head = head.next;
    return tempNode;
  }

  // peek()
  // pre: !isEmpty()
  // post: returns item at front of Queue
  public T peek() throws QueueEmptyException {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: peek() called on empty queue");
    }
    // Node<T> tempNode = head;
    // return tempNode;
    return head;
  }

  // dequeueAll()
  // sets this Queue to the empty state
  // pre: !isEmpty()
  // post: isEmpty()
  public void dequeueAll() throws QueueEmptyException {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: dequeueAll() called on empty queue");
    }
    head = tail = null;
  }

  // toString()
  // overrides Object's toString() method
  public String toString() {

  }
}
