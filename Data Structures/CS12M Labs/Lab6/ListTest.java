/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 8/3/2015
  File Description: A Java file for
  testing various methods from List.java
  File Name: ListTest.java
 */

public class ListTest {
	public static void main(String[] args) {
		  //List L = new List();
		  List <String>L = new <String>List();
		  
		  //TEST isEmpty(), size(), add()
		  //System.out.println(L.isEmpty());
		  //System.out.println(L.size());
		  L.add(1, "a");
		  L.add(2, "b");
		  L.add(3, "c");
		  //L.add(3,  1);
		  //System.out.println(L.isEmpty());
		  //System.out.println(L.size());
		  //L.add(2, "d");
		  //L.add(4, "e");
		  
		  //TEST get()
		  //System.out.println(L.get(0));
		  //System.out.println(L.get(6));
		  //System.out.println(L.get(2));
		  System.out.println(L.get(3));
		  
		  //TEST remove()
		  //only one line is uncommented per trial run
		  //L.remove(0);
		  //L.remove(1);
		  //L.remove(2);
		  //L.remove(5);
		  //L.remove(7);
		  //System.out.println(L.get(4));
		  
		  //TEST removeAll()
		  //L.removeAll();
		  //System.out.println(L.isEmpty());
		  //System.out.println(L.get(1));
	}
}
