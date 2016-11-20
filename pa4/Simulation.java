//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// Simulation.java
// PA4
// Runs a simulation of jobs going through processor queues
// and returns the output to a report and trace file
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Simulation{

  // Returns the job that should be created based on the information
  // given on each line of the input file
  public static Job getJob(String input) {
    String[] inputArray = input.split(" ");
    int arrival = Integer.parseInt(inputArray[0]);
    int duration = Integer.parseInt(inputArray[1]);
    return new Job(arrival, duration);
  }

  // Prints the array that stores all of the queues
  public static void printProcessorArray(Queue[] mainProcessorArray, PrintWriter output_trc) {
    for (int i = 0; i < mainProcessorArray.length; i++) {
      output_trc.println(i+": "+mainProcessorArray[i]);
    }
  }

  // Checks if all of the processor queues are empty and that all jobs
  // have been assigned and completed
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
    // Instantiates fields
    // ******************************************************
    int time = 0;
    boolean allJobsComplete = false;
    String totalWait = "";
    String maxWait = "";
    String averageWait = "";
    Queue backupQueue = new Queue();

    // Keeps track of the number of jobs left in the main wait queue
    int jobsLeft = inputQueue.length();

    // Keeps track of when the state of the main processor array
    // changes so that we know when to print
    boolean stateChanged = false;

    // Creates array of processors(queues) that will be handle all of the jobs
    Queue[] mainProcessorArray = new Queue[numProcessors+1];
    // ******************************************************

    // Stores the jobs in a backup queue to reset the jobs between
    // simulation runs
    for (int i = 0; i < inputQueue.length(); i++) {
      backupQueue.enqueue(inputQueue.peekAnywhere(i+1));
    }

    // Creates an empty queue at each position in the mainProcessorArray
    for (int i = 0; i < mainProcessorArray.length; i++) {
      mainProcessorArray[i] = new Queue();
    }

    // Populates the main queue from the backup queue at the beginning
    // of each simulation
    for (int i = 0; i < backupQueue.length(); i++) {
      mainProcessorArray[0].enqueue(backupQueue.peekAnywhere(i+1));
    }


    // Formatted printing for output_trc file
    //************************************************************
    output_trc.println("*****************************");
    String correctFormat = (numProcessors==1) ? " processor:" : " processors:";
    output_trc.println(numProcessors+correctFormat);
    output_trc.println("*****************************");

    output_trc.println("time=0");
    printProcessorArray(mainProcessorArray, output_trc);
    output_trc.println();
    //************************************************************

    // Loop that will run through the main wait queue and control the
    // movement of jobs in the array
    while(!allJobsComplete) {

      // Controls the movement of current jobs that processors
      // are working on and checks if they are done at the current time
      for (int i = 1; i < mainProcessorArray.length; i++) {
        if (mainProcessorArray[i].length() > 0) {
          Job currentJob = (Job) mainProcessorArray[i].peek();
          if (currentJob.getFinish() <= time) {
            // Add the job back to the main storage queue which will
            // now contain both completed and uncompleted jobs
            mainProcessorArray[0].enqueue(currentJob);
            mainProcessorArray[i].dequeue();

            // If the queue that just finished the past job still
            // has other jobs, calculate the finish time of the next
            // job in the queue
            if (mainProcessorArray[i].length() > 0) {
              // Store the new job that the processor will work on
              Job newJob = (Job) mainProcessorArray[i].peek();

              // Because the new job is now done waiting, we can
              // compute the finish time of that job
              newJob.computeFinishTime(time);

            }
            stateChanged = true;
          }
        }
        allJobsComplete = checkAllJobsComplete(mainProcessorArray,jobsLeft);
      }

      // Only move jobs to processors if there are actually jobs left
      if (jobsLeft > 0) {
        // Controls the movement of jobs that have yet to be processed
        for (int i = 0; i < mainProcessorArray[0].length(); i++) {
          Job nextJob = (Job) mainProcessorArray[0].peek();

          // Controls movement of jobs that need starting
          if (nextJob.getArrival() == time) {
            int indexShortestQueue = findSmallestQueue(mainProcessorArray);

            // If the job is being added to an empty queue, it will
            // not have to wait and so we can calculate the finish time
            if (mainProcessorArray[indexShortestQueue].length()==0) {
              nextJob.computeFinishTime(time);
            }
            // Add the job to the shortest queue and remove it from
            // the main wait queue
            mainProcessorArray[indexShortestQueue].enqueue(nextJob);
            mainProcessorArray[0].dequeue();
            stateChanged = true;
            jobsLeft--;
          }
        }
      }

      // Prints out the state of all processors if any of the jobs
      // were started or finished at the current time
      if (stateChanged) {
        output_trc.println("time="+time);
        printProcessorArray(mainProcessorArray, output_trc);
        output_trc.println();
        // Reset the change state back to false so that output
        // is correctly printed
        stateChanged = false;
      }
      time++;
    }

    // Calculate data needed for .rpt file and print to file
    totalWait = String.valueOf(mainProcessorArray[0].totalWaitTime());
    maxWait = String.valueOf(mainProcessorArray[0].maxWaitTime());
    averageWait = mainProcessorArray[0].averageWaitTime();
    output_rpt.println(numProcessors+correctFormat+" "+"totalWait="+totalWait+", "+"maxWait="+maxWait+", "+"averageWait="+averageWait);

  }

  // returns the index of the smallest queue in the processor array
  public static int findSmallestQueue(Queue[] mainProcessorArray) {
    // Start with the size of the first processor queue
    int smallestSize = mainProcessorArray[1].length();
    int index = 1;

    // Start at index 2 because we already set smallestSize to index 1
    for (int i = 2; i < mainProcessorArray.length; i++) {
      if (mainProcessorArray[i].length() < smallestSize) {
        smallestSize = mainProcessorArray[i].length();
        index = i;
      }
    }
    return index;
  }

  public static void main(String[] args) throws IOException{

    // Instantiate all objects needed
    Queue originalInputQueue = new Queue();
    Scanner in;
    PrintWriter output_trc, output_rpt;
    int numJobs = 0;
    Job tempJob;

    // Check number of command line arguments is at least 1 and exit if
    // no input file was given
    if (args.length < 1){
      System.out.println("Usage: Simulation <input file>");
      System.exit(1);
    }

    // Create a new scanner to read the input file and PrintWriters
    // to write the output
    in = new Scanner(new File(args[0]));
    output_trc = new PrintWriter(new FileWriter(args[0]+".trc"));
    output_rpt = new PrintWriter(new FileWriter(args[0]+".rpt"));

    // Read the number of jobs from the first line of the input file
    numJobs = Integer.parseInt(in.nextLine());

    // Reads the jobs in from the input file and adds them to
    // the main storage queue
    while( in.hasNextLine() ){
      String temp = in.nextLine();
      if (!temp.isEmpty()) {
        tempJob = getJob(temp);
        originalInputQueue.enqueue(tempJob);
      }
    }

    int numProcessors = numJobs-1;

    // Formatted output for the .trc file
    output_trc.println("Trace file: "+args[0]+".trc");
    output_trc.println(originalInputQueue.length()+" Jobs:");
    output_trc.println(originalInputQueue);
    output_trc.println();

    // Formatted output for the .rpt file
    output_rpt.println("Report file: "+args[0]+".rpt");
    output_rpt.println(originalInputQueue.length()+" Jobs:");
    output_rpt.println(originalInputQueue);
    output_rpt.println();
    output_rpt.println("***********************************************************");


    // Loop that runs simulations from to numJobs-1 processors
    for (int i = 1; i < numJobs; i++) {
      testWithProcessors(i,originalInputQueue,output_trc,output_rpt);
      // Resets the finish times between each simulation run
      originalInputQueue.resetJobFinishTimes();
    }

    // Closes input and output objects
    in.close();
    output_trc.close();
    output_rpt.close();
  }
}
