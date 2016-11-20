//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// QueueTest.java
// PA 4
// Tests many of the functions in Queue.java for functionality
// and correctness
//-----------------------------------------------------------------------------
class QueueTest {
 public static void main(String[] args) {
   Queue testQueue = new Queue();
   Job job1 = new Job(2,2);
   Job job2 = new Job(3,4);
   Job job3 = new Job(5,6);
   testQueue.enqueue(job1);
   testQueue.enqueue(job2);
   testQueue.enqueue(job3);
   System.out.println(testQueue);
   System.out.println("Is Empty: "+testQueue.isEmpty());
   System.out.println("Length: "+testQueue.length());
   System.out.println("Calling dequeue");
   System.out.println(testQueue.dequeue());
   System.out.println(testQueue);
   System.out.println("Is Empty: "+testQueue.isEmpty());
   System.out.println("Length: "+testQueue.length());
   System.out.println("Calling peek");
   System.out.println(testQueue.peek());
   System.out.println("Calling dequeueAll");
   testQueue.dequeueAll();
   System.out.println("Is Empty: "+testQueue.isEmpty());
   System.out.println("Length: "+testQueue.length());
 }
}
