//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// Queue.java
// PA4
// Implements QueueInterface<T> and has methods that allow
// easy operations on the Queue
//-----------------------------------------------------------------------------

import java.text.DecimalFormat;
import java.text.NumberFormat;

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

  // peekAnywhere()
  // pre: !isEmpty()
  // post: returns item at any specified position
  public Job peekAnywhere(int position) {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: peekAnywhere() called on empty queue");
    }
    Node tracer = head;
    int counter = 1;
    while(counter < position) {
      tracer = tracer.next;
      counter++;
    }
    return tracer.job;
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

  // resetJobFinishTimes()
  // pre: !isEmpty
  // post: resets all job finish times in this queue
  public void resetJobFinishTimes() {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: resetJobFinishTimes() called on empty queue");
    }
    Node tracer = head;
    while(tracer!=null) {
      tracer.job.resetFinishTime();
      tracer = tracer.next;
    }
  }

  // totalWaitTime()
  // pre: !isEmpty
  // post: returns the total wait time of all of the jobs
  // in this queue
  public int totalWaitTime() {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: totalWaitTime() called on empty queue");
    }
    Node tracer = head;
    int sum = 0;
    // loop through the queue and adds all the wait times
    while(tracer!=null) {
      sum+=tracer.job.getWaitTime();
      tracer = tracer.next;
    }
    return sum;
  }

  // maxWaitTime()
  // pre: !isEmpty
  // post: returns the maximum wait time of a job in this queue
  public int maxWaitTime() {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: maxWaitTime() called on empty queue");
    }
    Node tracer = head;
    int max = 0;
    // loops through the queue and looks for highest wait time
    while(tracer!=null) {
      if (tracer.job.getWaitTime() > max) {
        max = tracer.job.getWaitTime();
      }
      tracer = tracer.next;
    }
    return max;
  }

  // averageWaitTime()
  // pre: !isEmpty
  // post: returns the average wait time of all jobs in this queue
  public String averageWaitTime() {
    if (numItems < 1) {
      throw new QueueEmptyException("Queue.java Error: averageWaitTime() called on empty queue");
    }
    // makes sure that the return value has two decimal places
    DecimalFormat dec = new DecimalFormat("0.00");
    dec.setMinimumFractionDigits(2);
    double average = (double)this.totalWaitTime()/this.length();
    return String.valueOf(dec.format(average));
  }
}
