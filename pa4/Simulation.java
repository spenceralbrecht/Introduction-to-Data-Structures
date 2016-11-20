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

  public static Job getJob(String input) {
    String[] inputArray = input.split(" ");
    int arrival = Integer.parseInt(inputArray[0]);
    int duration = Integer.parseInt(inputArray[1]);
    // int arrival = Integer.parseInt(input.substring(0,1));
    // int duration = Integer.parseInt(input.substring(2));
    return new Job(arrival, duration);

    // String[] s = in.nextLine().split(" ");
		// int a = Integer.parseInt(s[0]);
		// int d = Integer.parseInt(s[1]);
		// return new Job(a, d);
  }

  public static void printProcessorArray(Queue[] mainProcessorArray, PrintWriter output_trc) {
    for (int i = 0; i < mainProcessorArray.length; i++) {
      output_trc.println(i+": "+mainProcessorArray[i]);
      // System.out.println(i+": "+mainProcessorArray[i]);
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

  // Runs the simulation with the specified number of processors and prints
  // out the output to the correct file
  public static void testWithProcessors(int numProcessors, Queue inputQueue, PrintWriter output_trc, PrintWriter output_rpt) {
    int time = 0;
    String totalWait = "";
    String maxWait = "";
    String averageWait = "";

    // Array of processors(queues) that will be handle all of the jobs
    Queue[] mainProcessorArray = new Queue[numProcessors+1];
    Queue backupQueue = new Queue();

    for (int i = 0; i < inputQueue.length(); i++) {
      backupQueue.enqueue(inputQueue.peekAnywhere(i+1));
    }
    int numJobs = inputQueue.length();

    // // rebuilds inputQueue
    // for (int i = 0; i < numJobs; i++) {
    //   Job tempJob = backupQueue.dequeue();
    //   inputQueue.enqueue(tempJob);
    // }

    // System.out.println(backupQueue);

    // System.out.println("line 63");

    // Loop that creates an empty queue at each position in the mainProcessorArray
    for (int i = 0; i < mainProcessorArray.length; i++) {
      mainProcessorArray[i] = new Queue();
    }

    for (int i = 0; i < backupQueue.length(); i++) {
      mainProcessorArray[0].enqueue(backupQueue.peekAnywhere(i+1));
    }

    // System.out.println("line 70");
    // System.out.println(Arrays.toString(mainProcessorArray));
    // System.out.println("Array length = "+mainProcessorArray.length);

    // System.out.println("length = "+mainProcessorArray[0].length());
    // System.out.println("line 73");

    // // emptys main wait list before rebuilding between simulations
    // if (mainProcessorArray[0].length()!=0) {
    //   mainProcessorArray[0].dequeueAll();
    // }

    // // copies backupQueue into main waitlist but destroys
    // // backupQueue in process
    // for (int i = 0; i < numJobs; i++) {
    //   Job tempJob = backupQueue.dequeue();
    //   mainProcessorArray[0].enqueue(tempJob);
    // }
    //
    // // rebuilds backupQueue
    // for (int i = 0; i < numJobs; i++) {
    //   Job tempJob = inputQueue.dequeue();
    //   backupQueue.enqueue(tempJob);
    // }
    //
    // // rebuilds inputQueue
    // for (int i = 0; i < numJobs; i++) {
    //   Job tempJob = backupQueue.dequeue();
    //   inputQueue.enqueue(tempJob);
    // }




    // System.out.println("mainProcessorArray[0]"+mainProcessorArray[0].toString());

    // Formatted printing for output_trc file
    //************************************************************
    output_trc.println("*****************************");
    String correctFormat = (numProcessors==1) ? " processor: " : " processors: ";
    output_trc.println(numProcessors+correctFormat);
    output_trc.println("*****************************");

    output_trc.println("time=0");
    printProcessorArray(mainProcessorArray, output_trc);
    output_trc.println();
    //************************************************************

    // System.out.println("*****************************");
    // System.out.println(numProcessors+correctFormat);
    // System.out.println("*****************************");
    //
    // System.out.println("time=0");
    // printProcessorArray(mainProcessorArray, output_trc);
    // System.out.println();


    // Keeps track of when the state of the main processor array
    // changes so that we know when to print
    boolean stateChanged = false;

    // Keeps track of the number of jobs left in the main wait queue
    int jobsLeft = mainProcessorArray[0].length();

    boolean allJobsComplete = false;

    // Loop that will run through the main wait queue and control the
    // movement of jobs in the array
    while(!allJobsComplete) {
      // // only move jobs to processors if there are actually jobs left
      // if (jobsLeft > 0) {
      //   // controls the movement of jobs that have yet to be processed
      //   for (int i = 0; i < mainProcessorArray[0].length(); i++) {
      //     Job nextJob = (Job) mainProcessorArray[0].peek();
      //
      //     // controls movement of jobs that need starting
      //     if (nextJob.getArrival() == time) {
      //       int indexShortestQueue = findSmallestQueue(mainProcessorArray);
      //
      //       // if the job is being added to an empty queue, it will
      //       // not have to wait and so we can calculate the finish time
      //       if (mainProcessorArray[indexShortestQueue].length()==0) {
      //         nextJob.computeFinishTime(time);
      //       }
      //
      //       mainProcessorArray[indexShortestQueue].enqueue(nextJob);
      //       mainProcessorArray[0].dequeue();
      //       stateChanged = true;
      //       jobsLeft--;
      //     }
      //   }
      // }


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

      // prints out the state of all processors if any of the jobs
      // were started or finished at the current time
      if (stateChanged) {
        output_trc.println("time="+time);
        // System.out.println("time="+time);
        printProcessorArray(mainProcessorArray, output_trc);
        output_trc.println();
        // System.out.println();
        // reset the change state back to false so that output
        // is correctly printed
        stateChanged = false;
      }
      time++;
    }

    totalWait = String.valueOf(mainProcessorArray[0].totalWaitTime());
    maxWait = String.valueOf(mainProcessorArray[0].maxWaitTime());
    averageWait = mainProcessorArray[0].averageWaitTime();
    output_rpt.println(numProcessors+correctFormat+"totalWait="+totalWait+", "+"maxWait="+maxWait+", "+"averageWait ="+averageWait);

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


  public static void main(String[] args) throws IOException{

    // create a queue to store the original input
    // data and add each job to it
    Queue originalInputQueue = new Queue();
    int numJobs = 0;
    Job tempJob;

    // check number of command line arguments is at least 1
    if (args.length < 1){
      System.out.println("Usage: Simulation <input file>");
      System.exit(1);
    }

    // open file
    Scanner in = new Scanner(new File(args[0]));
    PrintWriter output_trc = new PrintWriter(new FileWriter(args[0]+".trc"));
    PrintWriter output_rpt = new PrintWriter(new FileWriter(args[0]+".rpt"));

    numJobs = Integer.parseInt(in.nextLine());

    // read lines from in, write lines to out
    while( in.hasNextLine() ){
      String temp = in.nextLine();
      if (!temp.isEmpty()) {
        tempJob = getJob(temp);
        originalInputQueue.enqueue(tempJob);
      }
    }

    int numProcessors = numJobs-1;

    output_trc.println("Trace file: "+args[0]+".trc");
    output_trc.println(originalInputQueue.length()+" Jobs:");
    output_trc.println(originalInputQueue);
    output_trc.println();

    output_rpt.println("Report file: "+args[0]+".rpt");
    output_rpt.println(originalInputQueue.length()+" Jobs:");
    output_rpt.println(originalInputQueue);
    output_rpt.println();
    output_rpt.println("***********************************************************");

    // System.out.println("Got to line 245");

    // loop that runs simulations with 1 to numJobs-1 processors
    for (int i = 1; i < numJobs; i++) {
      testWithProcessors(i,originalInputQueue,output_trc,output_rpt);
      // System.out.println("originalInputQueue in main in loop "+originalInputQueue.toString());
      originalInputQueue.resetJobFinishTimes();
    }

    // close files
    in.close();
    output_trc.close();
    output_rpt.close();
  }
}
