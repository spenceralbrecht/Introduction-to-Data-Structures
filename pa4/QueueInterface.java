//-----------------------------------------------------------------------------
// QueueInterface.java
// interface for the Queue ADT
//-----------------------------------------------------------------------------
@SuppressWarnings("Overrides")
public interface QueueInterface<T>{

   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty();

   // length()
   // pre: none
   // post: returns the length of this Queue.
   public int length();

   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(T newItem);

   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public T dequeue() throws QueueEmptyException;

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public T peek() throws QueueEmptyException;

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException;

   // toString()
   // overrides Object's toString() method
   public String toString();
}
