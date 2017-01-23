/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 8/3/2015
  File Description: Contains class List 
  and private class Node. Implements 
  ListInterface and is part of the List ADT.
  File Name: List.java
 */

public class List<T> implements ListInterface<T> {
	
	private class Node {
		private T item;
		private Node next;
		
		//constructor
		Node(T x) {
			item = x;
			next = null;
		}
	}
	
	private Node head;
	private int numItems;
	
	//constructor
	public List() {
		head = null;
		numItems = 0;
	}
	
	// find()
	// returns a reference to the Node at position index in this List
	private Node find(int index){
	      Node N = head;
	      for(int i=1; i<index; i++) N = N.next;
	      return N;
	   }
	
	// ADT operations ----------------------------------------------------------
	
	// isEmpty
	// pre: none
	// post: returns true if this List is empty, false otherwise
	public boolean isEmpty() {
		boolean state = (numItems == 0);
		return state;
	}
	
	// size
	// pre: none
	// post: returns the number of elements in this List
	public int size() {
		return numItems;
	}

	// get
	// pre: 1 <= index <= size()
	// post: returns item at position index
	public T get(int index) throws ListIndexOutOfBoundsException {
		if( index<1 || index>numItems ) { 
			throw new ListIndexOutOfBoundsException 
				("List Error: get() called on invalid index: " + index);
		}
		return find(index).item;
	}

	// add
	// inserts newItem in this List at position index
	// pre: 1 <= index <= size()+1
	// post: !isEmpty(), items to the right of newItem are renumbered
	@Override
	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
		if( index<1 || index>(numItems+1) ) {
			throw new ListIndexOutOfBoundsException 
				("List Error: add() called on invalid index: " + index);
		}
		//case for adding the head
		if( index == 1 ) {
			Node N = new Node(newItem);
			N.next = head;
			head = N;
		}
		else {
			Node N = new Node(newItem);
			N.next = find(index);
			find(index-1).next = N;
		}
		numItems++;
	}

	// remove
	// deletes item from position index
	// pre: 1 <= index <= size()
	// post: items to the right of deleted item are renumbered
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if( index<1 || index>numItems ) {
			throw new ListIndexOutOfBoundsException
				("List Error: remove() called on invalid index: " + index);
		}
		//case for removing head
		if( index == 1 ) head = head.next;
		
		else { 
			find(index-1).next = find(index).next;
		}
		numItems--;
	}

	// removeAll
	// pre: none
	// post: isEmpty()
	public void removeAll() {
		head.next = null;
		head = null;
		numItems = 0;
	}
	
	// toString()
	// pre: none
	// post:  prints current state to stdout
	// Overrides Object's toString() method
	@Override
	public String toString(){
	      StringBuffer sb = new StringBuffer();
	      Node N = head;

	      for( ; N!=null; N=N.next) sb.append(N.item).append(" ");
	      return new String(sb);
	   }

}
				  
	
		
	
