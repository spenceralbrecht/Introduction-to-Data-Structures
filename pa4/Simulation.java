//-----------------------------------------------------------------------------
// SimulationStub.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

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


  public static void printProcessorArray(Queue[] mainProcessorArray) {
    for (int i = 0; i < mainProcessorArray.length; i++) {
      System.out.println(i+": "+mainProcessorArray[i]);
    }
  }

  public static boolean checkAllJobsComplete(Queue[] mainProcessorArray, int jobsLeft) {
    boolean returnValue = false;
    if (jobsLeft == 0) {
      for (int i = 1; i< mainProcessorArray.length; i++) {
        if (mainProcessorArray[i].length()>0) {
          return false;
        }
      }
      returnValue = true;
    }
    return returnValue;
  }

  // runs the simulation with the specified number
  // of processors and prints out the info to the file
  public static void testWithProcessors(int numProcessors, Queue inputQueue) {
    int time = 0;
    // array of processors that will be running through the
    // jobs
    Queue[] mainProcessorArray = new Queue[numProcessors+1];

    // processor at index 0 will contain all of the original
    // jobs from the file input
    mainProcessorArray[0] = inputQueue;

    // Loop that creates an empty queue at each position in the
    // processor array
    for (int i = 1; i < mainProcessorArray.length; i++) {
      mainProcessorArray[i] = new Queue();
    }

    // Formatted printing for output
    //************************************************************
    System.out.println("*****************************");
    String correctFormat = (numProcessors==1) ? " processor:" : " processors:";
    System.out.println(numProcessors+correctFormat);
    System.out.println("*****************************");

    System.out.println("time=0");
    printProcessorArray(mainProcessorArray);
    System.out.println();
    //************************************************************

    // keeps track of when the state of the main processor array
    // changes so that we know when to print
    boolean stateChanged = false;

    int jobsLeft = mainProcessorArray[0].length();

    boolean allJobsComplete = false;

    // loop that will run through the main wait queue and
    // control the movement of jobs in the array
    while(!allJobsComplete) {
      // only move jobs to processors if there are actually jobs left
      if (jobsLeft > 0) {
        // controls the movement of jobs that have yet to be processed
        for (int i = 0; i < mainProcessorArray[0].length(); i++) {
          Job nextJob = (Job) mainProcessorArray[0].peek();

          // controls movement of jobs that need starting
          if (nextJob.getArrival() == time) {
            int indexShortestQueue = findSmallestQueue(mainProcessorArray);

            // if the job is being added to an empty queue, it will
            // not have to wait and so we can calculate the finish time
            if (mainProcessorArray[indexShortestQueue].length()==0) {
              nextJob.computeFinishTime(time);
            }

            mainProcessorArray[indexShortestQueue].enqueue(nextJob);
            mainProcessorArray[0].dequeue();
            stateChanged = true;
            jobsLeft--;
          }
        }
      }


      // controls the movement of current jobs that processors
      // are working on and checks if they are done at the
      // current time
      for (int i = 1; i < mainProcessorArray.length; i++) {
        if (mainProcessorArray[i].length() > 0) {
          Job currentJob = (Job) mainProcessorArray[i].peek();
          if (currentJob.getFinish() <= time) {
            // add the job back to the main storage queue which will
            // now contain both completed and uncompleted jobs
            mainProcessorArray[0].enqueue(currentJob);
            mainProcessorArray[i].dequeue();

            // if the queue that just finished the past job still
            // has other jobs, calculate the finish time of the next
            // job in the queue
            if (mainProcessorArray[i].length() > 0) {
              // store the new job that the processor will work on
              Job newJob = (Job) mainProcessorArray[i].peek();

              // because the new job is now done waiting, we can
              // compute the finish time of that job
              newJob.computeFinishTime(time);

            }
            stateChanged = true;
          }
        }
        allJobsComplete = checkAllJobsComplete(mainProcessorArray,jobsLeft);
      }

      // System.out.println();
      // System.out.println("state changed = "+stateChanged);
      // System.out.println("time="+time);
      // System.out.println("working processors = "+workingProcessors);
      // printProcessorArray(mainProcessorArray);
      // System.out.println();
      // prints out the state of all processors if any of the jobs
      // were started or finished at the current time
      if (stateChanged) {
        System.out.println("time="+time);
        printProcessorArray(mainProcessorArray);
        System.out.println();
        // reset the change state back to false so that output
        // is correctly printed
        stateChanged = false;
      }
      time++;
    }

  }

  // returns the index of the smallest queue in the processor array
  public static int findSmallestQueue(Queue[] mainProcessorArray) {
    // start with the size of the first processor queue
    int smallestSize = mainProcessorArray[1].length();

    // start at index 2 because the index 0 has the main wait queue
    // and if the array only has one processor then index 1 will
    // be returned
    int index = 1;
    for (int i = 2; i < mainProcessorArray.length; i++) {
      if (mainProcessorArray[i].length() < smallestSize) {
        smallestSize = mainProcessorArray[i].length();
        index = i;
      }
    }
    return index;
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

    int numJobs = 0;
    int arrival = 0;
    int duration = 0;
    String fileName;
    // create a queue to store the original input
    // data and add each job to it
    Queue originalInputQueue = new Queue();

    // check number of command line arguments is at least 2
    if (args.length < 1){
      System.out.println("Usage: Simulation <input file>");
      System.exit(1);
    }
    else {
      // save the input file name so that we can write to output
      // files with the same name
      fileName = args[0];
      // open file
      Scanner in = new Scanner(new File(args[0]));
      boolean firstLine = true;
      int lineNumber = 1;

      // read lines from in, write lines to out
      while( in.hasNextLine() ){
        if (firstLine) {
          numJobs = Integer.parseInt(in.nextLine());
          firstLine = false;
          lineNumber++;
        }
        else {
          String temp = in.nextLine();
          if (!temp.isEmpty()) {
            System.out.println("line read in ="+temp);
            arrival = Integer.parseInt(temp.substring(0,1));
            duration = Integer.parseInt(temp.substring(2));
            Job tempJob = new Job(arrival, duration);
            originalInputQueue.enqueue(tempJob);
            lineNumber++;
          }
        }
      }
    }

    int numProcessors = numJobs-1;

    System.out.println("number of jobs = "+numJobs);

    System.out.println(originalInputQueue.length()+" Jobs:");
    System.out.println(originalInputQueue);
    System.out.println();

    for (int i = 1; i < numJobs; i++) {
      testWithProcessors(i,originalInputQueue);
      originalInputQueue.resetJobFinishTimes();
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
