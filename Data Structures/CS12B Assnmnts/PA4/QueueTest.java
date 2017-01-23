
/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12B
  Date: 8/2/2015
  File Description: A Java file for testing
  various methods in the Queue class.
  File Name: QueueTest.java
*/
public class QueueTest {

        public static void main(String[] args) {
                Queue Q = new Queue();

                //TEST isEmpty(), length(), and enqueue()
                //System.out.println(Q.isEmpty());
                //System.out.println(Q.length());
                Q.enqueue("object1");
                Q.enqueue("object2");
                //System.out.println(Q.isEmpty());
                //System.out.println(Q.length());

                //TEST toString(), and dequeue()
                //System.out.println(Q.toString());
                //System.out.println(Q.dequeue() + "\n");
                //System.out.println(Q.toString());
                //System.out.println(Q.length());

                //TEST peek()
                //System.out.println(Q.peek() + "\n");
                //System.out.println(Q.toString());

                //TEST dequeueAll()
                Q.dequeueAll();
                System.out.println(Q.toString());

        }

}
