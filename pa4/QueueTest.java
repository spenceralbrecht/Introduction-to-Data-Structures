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
 }
}
