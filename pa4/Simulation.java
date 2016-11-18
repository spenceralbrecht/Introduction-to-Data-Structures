//-----------------------------------------------------------------------------
// SimulationStub.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

 public static Job getJob(Scanner in) {
    String[] s = in.nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a, d);
 }

 // runs the simulation with the specified number
 // of processors and prints out the info to the file
 public void testWithProcessors(int numProcessors, Queue[] inputQueue) {
   int time = 0;
   // array of processors that will be running through the
   // jobs
   Queue[] mainProcessorArray = new Queue[numProcessors];

   // main queue that all of the processor queues will be pulling
   // from that has a length = total number of jobs
   Queue mainWaitQueue = inputQueue;

   // empty queue that will be added to as jobs complete
   Queue completedQueue = new Queue();
 }

//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

     int m = 3 // number of m jobs

     // stuff that will be read from file later
     int numJobs = m;
     int numProcessors = m-1;

     // read each job from input file
     Job job1 = new Job(2,2);
     Job job2 = new Job(3,4);
     Job job3 = new Job(5,6);

     // create a queue to store the original input
     // data and add each job to it
     Queue originalInputQueue = new Queue();
     originalInputQueue.enqueue(job1);
     originalInputQueue.enqueue(job2);
     originalInputQueue.enqueue(job3);

     // loop that runs through from 1 to numProcessors
     // running the simulation each time
     for (int i = 0; i < numProcessors; i++) {
       testWithProcessors(i);
     }



//    1.  check command line arguments
//
//    2.  open files for reading and writing
//
//    3.  read in m jobs from input file
//
//    4.  run simulation with n processors for n=1 to n=m-1  {
//
//    5.      declare and initialize an array of n processor Queues and any
//            necessary storage Queues
//
//    6.      while unprocessed jobs remain  {
//
//    7.          determine the time of the next arrival or finish event and
//                update time
//
//    8.          complete all processes finishing now
//
//    9.          if there are any jobs arriving now, assign them to a processor
//                Queue of minimum length and with lowest index in the queue array.
//
//    10.     } end loop
//
//    11.     compute the total wait, maximum wait, and average wait for
//            all Jobs, then reset finish times
//
//    12. } end loop
//
//    13. close input and output files

   }
}
