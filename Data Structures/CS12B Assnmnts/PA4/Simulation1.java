/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12B
  Date: 8/2/2015
  File Description: A Java file simulates the
  processing of m number of jobs with 1 through
  m-1 processors. Reads and input file and writes
  output to two separate output files. Contains the
  main class for the Simulation ADT.
  File Name: Simulation.java
*/

import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;

public class Simulation {
        static Scanner in = null;
        static PrintWriter rpt = null;
        static PrintWriter trc = null;
        static Queue[] processor;
        static int m, completed, justCompleted, time;
        static int totalWait = 0;
        static int maxWait = 0;
        static double averageWait = 0.00;
        static Queue reset = new Queue();

        private static void resetWait() {
                totalWait = 0;
                maxWait = 0;
                averageWait = 0.00;
        }
	
        private static void printWait(int n) {
                if( n == 1 ) {
                        rpt.print(n + " processor: " +
                                        "totalWait=" + totalWait + ", maxWait="  + maxWait);                                                                            
                        rpt.printf(", averageWait=%.2f%n", averageWait);
                }
                else {
                        rpt.print(n + " processors: " +
                                        "totalWait=" + totalWait + ", maxWait=" + maxWait);
                        rpt.printf(", averageWait=%.2f%n", averageWait);
                }
        }

	//resets the finish time for all Jobs after a simulation
        private static void resetQueue(Queue P) {
                Queue Q = new Queue();
                for( int i=0; i<m; i++ ) {
                        Q.enqueue(P.peek());
                        P.dequeue();
                        Job J = (Job) Q.peek();
                        J.resetFinishTime();
                        P.enqueue(J);
                        Q.dequeue();
                }
        }
	//helper function
	//finds the smallest Queue to place the next Job
        private static Queue findMin(Queue[] P, int n) {
                Queue min = P[1];
                for ( int i = 2; i < n+1; i++ ) {
                        try {
                                if( P[i].length() < min.length() ) min = P[i];
                        }
                        catch (NullPointerException e){
                        }
                }
                return min;
        }

        public static Job getJob(Scanner in) {
              String[] s = in.nextLine().split(" ");
              int a = Integer.parseInt(s[0]);
              int d = Integer.parseInt(s[1]);
              return new Job(a, d);
           }

        //KEY:
        //processor => Array of Queues
        //processor[i] => Queue
        //.peek() => first Job in Queue
	
	// Performs the bulk of the work for the simulation
	// Enqueues Jobs from the storage Queue to the smallest
	// Queue available. Also calls process() after adding a Job
	// or completing a job
        private static void work(Queue[] P, int n){
                completed = 0; //global var # of total completed Jobs 
		// Global var justCompleted: # of Jobs completed last
		// while loop iteration
		justCompleted = 0; 
                int length = m; //global var # of Jobs
                // Local var update: value of length from previous 
		// while loop iteration
		int update = 0; 
                // Local var prcsSafety: keep track of whether process() 
		// was called this while iteration
		boolean  prcsSafety; 
                
                while ( completed < m ) {
                        prcsSafety = false;
                        update = length;
                        length = processor[0].length(); 
			// Print when a job was added from storage or processed
                        if( length!=update || justCompleted!=0 ) {
                                trc.println("time=" + (time-1));
                                for( int i=0; i<n+1; i++) trc.println(i + ": " + processor[i]);
                                trc.println();
                        }
                        justCompleted = 0; //reset justCompleted
			// Iterates through the length of processor[0] (storage Queue)
			// and enqueues a Job if the arrival time == time
                        for( int j = 0; j < length; j++ ) { //processor[i].length(); j++ ) {
                                for( int q = 1 ; q < n+1; q++ ) process(P[q]);
                                prcsSafety = true;
                                Job J = (Job) P[0].peek();                                                                                       
                                if(time==J.getArrival()) {
                                        Queue min = findMin(P, n);
                                        min.enqueue(J); //add Job to next Queue
                                        P[0].dequeue(); //remove Job from present Queue
                                }
                        }
			// To continue to call process() once the above for loop 
			// is no longer called (all Jobs have been dequeued from storage)
                        if( !prcsSafety ) for( int q=1; q<(n+1); q++ ) process(P[q]);
                        time++;
                }
                trc.println("time=" + (time-1));
                for( int i=0; i<n+1; i++) trc.println(i + ": " + processor[i]);
        }

        //Handles processing the head of a passed Queue
        private static void process(Queue Q) {
                try {
                        Job head = (Job) Q.peek();
                        int finish = head.getFinish();
                        if( finish == -1 ) {
                                head.computeFinishTime(time);
                                finish = head.getFinish();
                        }
                        if( time >= finish ) {
                                processor[0].enqueue(head);
                                completed++;
                                justCompleted++;
                                int waitTime = head.getWaitTime();
                                totalWait = totalWait + waitTime;
                                if( waitTime > maxWait ) maxWait = waitTime;
                                Q.dequeue();
                                process(Q); // call again to process the new head
                        }
                }
                catch (NoSuchElementException e) {
                }
                catch (NullPointerException f) {
                }
        }

        public static void main(String[] args) throws IOException {

                // check number of command line arguments is at least 2
                if( args.length < 1){
                System.out.println("Usage: Simulation infile");
                System.exit(1);
                }

                //Scan input file and create rpt and trc output files
                in = new Scanner(new File(args[0]));
                rpt = new PrintWriter(new FileWriter(args[0].concat(".rpt")));
                trc = new PrintWriter(new FileWriter(args[0].concat(".trc")));

                //Read input and store int on line 1 as m
                String temp = in.next();
                m = Integer.parseInt(temp);
                in.nextLine();

                //Create array of m Queues where processor[0] is the
                //storage queue
                //Each element (Queue) in processor[1:m-2] represents a
                //simulation with i processors
                processor = new Queue[m];
                for( int i=0; i<m; i++ ) processor[i] = new Queue();

                //Read input and put all the jobs into the pending
                //queue (processor[0])
                while (in.hasNextLine()){
                        processor[0].enqueue(getJob(in));
                }

                //Create a copy of the initial state of processor[0]
                //to reset after each processor simulation
                for( int i=0; i<m; i++ ) reset.enqueue(processor[0].getIndex(i));

                //Output
                rpt.println(m + " Jobs:");
                rpt.println(processor[0]);
                rpt.println();
                rpt.println("***********************************************************");
                trc.println(m + " Jobs:");
                trc.println(processor[0]);
                trc.println();

                //Iterate through simulations with 1 through m-1 processors
                //where i is the number of processors
                for( int i=1; i<m; i++) {
                        trc.println("*****************************");
                        if( i == 1 ) trc.println(i + " processor:");
                        else {
                                trc.println(i + " processors:");
                        }
                        trc.println("*****************************\n");
                        time = 0;
                        trc.println("time=" + time);
                        for( int j=0; j<=i; j++ ) trc.println(j + ": " + processor[j]);
                        work(processor, i);
                        double tempTotalWait = (double) totalWait;
                        averageWait = tempTotalWait/m;
                        printWait(i);
                        processor[0].dequeueAll();
                        for( int j=0; j<m; j++ ) processor[0].enqueue(reset.getIndex(j));
                        resetWait();
                        resetQueue(processor[0]);
                }

                //close files
                in.close();
                rpt.close();
                trc.close();
        }
}

