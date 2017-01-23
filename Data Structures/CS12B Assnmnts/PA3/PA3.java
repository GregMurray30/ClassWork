

//ADT Dictionary implements the Dictionary Interface consisting of
//seven methods. This ADT utilizes the singly linked list with a 
//head insert with private class Node and private helper method
//findKey.

public class Dictionary implements DictionaryInterface{
	
	private class Node {
	    String key;
	    String value;
		Node next;
		
		private Node(String k, String v) {
			key = k;
			value = v;
			next = null;
		}
	}
	
	private Node head; //first element in Dictionary
	private int numItems; //number of elements in dictionary
	
	public Dictionary() {
		head = null;
		numItems = 0;
	}
	
	//Private helper function
	//Finds the Node before the Node with key arg key
	//Used for delete()
	private Node findKeyMinusOne(String key) {
		Node N = head;
		int counter = 1;
		for (int i = 1; i < numItems+1; i++) {
			if(N.key.equals(key)) break;
			N=N.next;
			counter++;
		}
		N = head;
		for (int i = 1; i < counter-1; i++) {
			N=N.next;
		}
		return N;
	}
	
	//Private helper function to find Node with key arg key
	private Node findKey(String key){
		Node N = head;
		for (int i = 1; i < numItems+1; i++) {
			if(N.key.equals(key)) break;
			N=N.next;
		}
		return N;
	}
	
	// isEmpty()
	// pre: none
	// returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty() {
		return numItems == 0;
	}

	// size()
	// pre: none
	// returns the number of entries in this Dictionary
	public int size() {
		return numItems;
	}
	
	// lookup()
	// pre: none
	// returns value associated key, or null reference if no such key exists
	public String lookup(String key) throws KeyNotFoundException {
		
		try{
			return findKey(key).value;
		} catch 
			(NullPointerException e) {
			return null;
		}
	}
	   
	// insert()
	// inserts new (key,value) pair into this Dictionary
	// pre: lookup(key)==null
	public void insert(String key, String value) throws DuplicateKeyException{
		if(lookup(key)!=null) {
			throw new DuplicateKeyException( 
					"cannot insert duplicate keys");
		}
		   
		
		Node N = new Node(key, value);
		N.next = head;
		head = N;
		numItems++;
	}
	  
	// delete()
	// deletes pair with the given key
	// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException {
		if(lookup(key)==null) {
			throw new KeyNotFoundException(
					"cannot delete non-existent key");
		}
		
		Node P = findKey(key);
		if(P.equals(head)){
		  head = P.next;
		}
		else if(P.next==null) {
			
	         
			Node C = findKeyMinusOne(key);
			Node N = C.next;
			C.next = N.next;
			C.next = null;
			
		}
		
		else {
			Node C = findKeyMinusOne(key);
	        Node N = C.next;
	         C.next = N.next;
	         N.next = null;
		}
		
		numItems--;
	}

	// makeEmpty()
	// pre: none
	public void makeEmpty() {
		head = null;
		numItems = 0;
	}

	//A private recursive function called myString() 
	//does the real work, then is called by toString()
	//pre:none
	private String myString(Node H){
		if( H==null ) {
			return "";
		}
		else {
		      return (myString(H.next) + H.key + " " + H.value + " " + "\n");
		}
	}
	   
	public String toString() {
		return myString(head);
	}

}
