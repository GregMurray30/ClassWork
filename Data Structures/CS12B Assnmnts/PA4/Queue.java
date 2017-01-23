
//*
//  Student Name: Greg Murray
//  SID: 1101589
//  Class: CS12B
//  Date: 8/2/2015
//  File Description: A Java file that implements the
//  QueueInterface and is based on a linked list data
//  structure. Used for the Simulation ADT
//  File Name: Queue.java
//  */

class Queue implements QueueInterface{
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

        //helper function
        public Object getIndex(int n) {
                Node N = head;
                for( int i=0; i<n; i++ ) {
                        N=N.next;
                }
                return N.item;
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
                //         "cannot call dequeue() on an empty Queue");
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
                //         "cannot call peek() on an empty Queue");
           return head.item;
   }

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() { //throws QueueEmptyException {
          //if(isEmpty()) throw new QueueEmptyException (
                //         "cannot call dequeueAll() on an empty Queue");
           int size = numItems;
           for (int i = 0; i < size; i++) {
                   Node N = head;
                   head = head.next;
                   N = null;
                   numItems--;
           }

   }
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
