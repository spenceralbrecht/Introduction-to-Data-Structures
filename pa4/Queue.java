// Spencer Albrecht
// salbrech
// Queue.java
// PA4
// Implements QueueInterface<T> and has methods that allow
// easy operations on the Queue
import java.text.DecimalFormat;

class Queue implements QueueInterface {

  private class Node {
    Job job;
    Node next;

    Node(Object job) {
      this.job = (Job) job;
      this.next = null;
    }
  }

  private Node head;
  private Node tail;
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
  public void enqueue(Object newItem) {
    Node insertNode = new Node(newItem);
    // special case if it is the first node
    if (numItems==0) {
      head = tail = insertNode;
    }
    else {
      tail.next = insertNode;
      tail = insertNode;
    }
    numItems++;
  }

  // dequeue()
  // deletes and returns item from front of this Queue
  // pre: !isEmpty()
  // post: this Queue will have one fewer element
  public Job dequeue() throws QueueEmptyException {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: dequeue() called on empty queue");
    }
    Node tempNode = head;
    head = head.next;
    numItems--;
    return tempNode.job;
  }

  // peek()
  // pre: !isEmpty()
  // post: returns item at front of Queue
  public Job peek() throws QueueEmptyException {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: peek() called on empty queue");
    }
    // Node<T> tempNode = head;
    // return tempNode;
    return head.job;
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
    numItems = 0;
  }

  // toString()
  // overrides Object's toString() method
  public String toString() {
    Node tracer = head;
    String returnString = "";
    while(tracer!=null) {
      returnString+=tracer.job.toString()+" ";
      tracer = tracer.next;
    }
    return returnString;
  }

  public void resetJobFinishTimes() {
    Node tracer = head;
    while(tracer!=null) {
      tracer.job.resetFinishTime();
      tracer = tracer.next;
    }
  }

  public int totalWaitTime(Queue mainWaitQueue) {
    Node tracer = head;
    int sum = 0;
    while(tracer!=null) {
      sum+=tracer.job.getWaitTime();
      tracer = tracer.next;
    }
    return sum;
  }

  public int maxWaitTime(Queue mainWaitQueue) {
    Node tracer = head;
    int max = 0;
    while(tracer!=null) {
      if (tracer.job.getWaitTime() > max) {
        max = tracer.job.getWaitTime();
      }
      tracer = tracer.next;
    }
    return max;
  }

  public double averageWaitTime(Queue mainWaitQueue) {
    double average = totalWaitTime(mainWaitQueue)/(mainWaitQueue.length());
    return average;
  }
}
