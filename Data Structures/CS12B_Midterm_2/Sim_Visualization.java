import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;

class Job{
   public static final int UNDEF = -1;
   private int arrival;
   private int duration;
   private int finish;

   // default constructor
   public Job(int arrival, int duration){
      this.arrival = arrival;
      this.duration = duration;
      this.finish = UNDEF;
   }

   // access functions
   public int getArrival(){return arrival;}
   public int getDuration(){return duration;}
   public int getFinish(){return finish;}
   public int getWaitTime(){
      if( finish==UNDEF ){
         System.err.println("Job: getWaitTime(): undefined finish time");
         System.exit(1);
      }
      return finish-duration-arrival;
   }

   // manipulation procedures
   public void computeFinishTime(int timeNow) {finish = timeNow + duration;}
   public void resetFinishTime(){finish = UNDEF;}

   // toString
   // overrides Object's toString() method
   public String toString(){
      return "("+arrival+", "
                +duration+", "
                +(finish==UNDEF?"*":String.valueOf(finish))+")";
   }
}
class Queue {
	private int numItems;
	private Node head, tail;
	
	private class Node {
		Object item;
		Node next;
		
		private Node(Object o) {
			item = o;
			next = null;
		}
	}
	public Queue() {
		head = null;
		tail = null;
		numItems = 0;
	}
	
	/*
	//public getter: head
	public Node getHead() {
		return head;
	}
	
	//public getter: tail
	public Node getTail() {
		return tail;
	}
	
   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty() {
	   return (numItems==0);
   }
  */
  
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
	   if( numItems == 0) {
		   Node N = new Node(newItem);
		   head = N;
		   tail = N;
		   numItems++;
	   }
	   else {
		   Node N = new Node(newItem);
		   tail.next = N;
		   tail = N;
		   numItems++;
	   }
	   
   }

   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() { //throws QueueEmptyException {
	  // if(isEmpty()) throw new QueueEmptyException (
		//	   "cannot call dequeue() on an empty Queue");
	   Node N = head;
	   head = head.next;
	   numItems--;
	   return N.item;
   }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() { //throws QueueEmptyException {
	  //if(isEmpty()) throw new QueueEmptyException (
		//	   "cannot call peek() on an empty Queue");
	   return head.item;
   }
  /*
   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() { //throws QueueEmptyException {
	  //if(isEmpty()) throw new QueueEmptyException (
		//	   "cannot call dequeueAll() on an empty Queue");
	   int size = numItems;
	   for (int i = 0; i < size; i++) {
		   Node N = head;
		   head = head.next;
		   N = null;
		   numItems--;
	   }
	   
   }*/
  //A private recursive function called myString() 
	//does the real work, then is called by toString()
	//pre:none
	private String myString(Node H){
		if( H==null ) {
			return "";
		}
		else {
		      return (H.item + " " + myString(H.next));
		}
	}
	   
	public String toString() {
		return myString(head);
	}
	
}


public class Simulation {
	//static Scanner in = null; 
	//static PrintWriter rpt = null; 
	//static PrintWriter trc = null; 
	static Queue[] processor;
	//static int lineCounter = 0;
	static int m, n;
	static int time = 0;
	static int waitTotal = 0;
	static int maxWait = 0;
	
	
	/*
	public static Job getJob(Scanner in) {
	      String[] s = in.nextLine().split(" ");
	      int a = Integer.parseInt(s[0]);
	      int d = Integer.parseInt(s[1]);
	      return new Job(a, d);
	   }
	*/
	
	//KEY:
	//processor => Array of Queues
	//processor[i] => Queue
	//.peek() => first Job in Queue 
	/*
	public static void work2(Queue[] processor){
		for( int i = 0; i < 3; i++ ) {
			for( ; time < processor.length; time++) {
				for( int j = 0; j < 3; j++ ) { //processor[i].length(); j++ ) {
					Job J = (Job) processor[i].peek(); //J => head of Queue #i
					if(time>=J.getArrival()) { //if head of Queue[i]'s arrival == time
						
						processor[i+1].enqueue(J); //add Job to next Queue
						processor[i].dequeue(); //remove Job from present Queue
					}
				}
				//process(processor[0], processor[i+1]);
			}
		}
	}
	*/
	
	public static void process(Queue Q0, Queue Q1) {
		 try {
			Job head = (Job) Q1.peek();
			if( head.getFinish() == -1 )
				head.computeFinishTime(time);
			if( time >= head.getFinish() ) {
				Q0.enqueue(head);
				waitTotal = waitTotal + head.getWaitTime();
				if( head.getWaitTime() > maxWait ) maxWait = head.getWaitTime();
				Q1.dequeue();
			}
		}
		 catch (NoSuchElementException e) {
		 }
		 catch (NullPointerException f) {
		 }
	}
		 

	
	
	public static void work1(Queue[] processor, int n){
		for( int i = 0; i < n+1; i++ ) {
			for( ; time < processor.length; time++) {
				for( int j = 0; j < 3; j++ ) { //processor[i].length(); j++ ) {
					Job J = (Job) processor[i].peek(); //J => head of Queue #i
					if(time>=J.getArrival()) { //if head of Queue[i]'s arrival == time
						processor[i+1].enqueue(J); //add Job to next Queue
						processor[i].dequeue(); //remove Job from present Queue
					}
				}
				process(processor[0], processor[i+1]);
			}
		}
	}
	
		
	
		
	
	
	
	/*Recursive method to automate peeking at subsequent Queue elements
	//through head.next
	public Job peekNext(Queue Q, int pos) {
		Job J = Q.head;
		if( pos == 0) {
			return
		}
	}
	*/
	public static void main(String[] args) { //throws IOException {
		
		// check number of command line arguments is at least 2
		//if( args.length < 1){
		//System.out.println("Usage: Simulation infile");
		//System.exit(1);
		//}
		
		//VISUALIZATION VERSION ONLY:
		int m = 3;
		//
		
		
		/*
		//Scan input file and create rpt and trc output files
		in = new Scanner(new File(args[0]));
		rpt = new PrintWriter(new FileWriter(args[0].concat(".rpt")));
		trc = new PrintWriter(new FileWriter(args[0].concat(".trc")));
		
		//Read input and store int on line 1 as m
		String temp = in.next();
		m = Integer.parseInt(temp);
		in.nextLine();
		*/
		
		//Create array of m Queues where processor[0] is the
		//storage queue
		//Each element (Queue) in processor[1:m-2] represents a 
		//simulation with i processors
		processor = new Queue[m];
		for (int i = 0; i < m; i++) processor[i] = new Queue();
		
		//VISUALIZATION VERSION ONLY
		processor[0].enqueue(new Job(1, 2));
		processor[0].enqueue(new Job(1, 3));
		processor[0].enqueue(new Job(3, 2));
		//
		
		//Read input and put all the jobs into the pending 
		//queue (processor[0])
		//while (in.hasNextLine()){
		//	processor[0].enqueue(getJob(in));
		//}
		
		
		//NEED CODE TO TELL WHEN THE SIMULATION IS COMPLETE
		
		
		//Iterate through simulations with 1 through m-2 processors
		//where i is the number of processors
		//for (int i = 1; i < m-1; i++) {
			//prcs = i;
			work1(processor, 1);
			System.out.println(processor[1].toString());
		//}
		
	}

}

