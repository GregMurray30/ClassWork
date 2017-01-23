/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 8/7/2015
  File Description: Contains class Dictionary 
  and private class Node. Implements the
  Dictionary Interface and is part of the 
  Dictionary ADT based on a BST with String keys
  and values.
  File Name: Dictionary.java
 */

import java.io.*;
import java.lang.String;

public class Dictionary implements DictionaryInterface {
	
	private class Node {
		String key, value;
		Node left, right;
		
		// Node constructor
		private Node(String k, String v) {
			key = k;
			value = v;
			left = null;
			right = null;
		}
	}
	
	// Dictionary class variables
	Node root;
	int numPairs;
	
	// Dictionary constructor
	public Dictionary() {
		root = null;
		numPairs = 0;
	}

	// private helper functions 
	//-----------------------------------------------------------
	
	// findKey()
	// returns the Node containing key k in the subtree rooted at R, or returns
	// NULL if no such Node exists
	private Node findKey(Node R, String key) {
		if( R==null || key.equals(R.key) ) return R;
		if( key.compareTo(R.key)>0 ) return findKey( R.right, key );
		else { return findKey( R.left, key ); }
	}
	
	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns NULL 
	// if N is equal to R.
	private Node findParent(Node N, Node R) {
		Node P = null;
		if( N!=R ) {
			P=R;
			while( P.left!=N && P.right!=N ) {
				if( N.key.compareTo(P.key)<0 ) P = P.left;
				else { P=P.right; }
			}
		}
		return P;
	}
	
	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, 
	// or NULL if R is NULL.
	private Node findLeftMost(Node R){
	   Node L = R;
	   if( L!=null ) for( ; L.left!=null; L=L.left) ;
	   return L;
	}
	
	// concatInOrder()
	// adds the (key, value) pairs belonging to the subtree rooted at R 
	// to a StringBuffer in order of increasing keys. Used for toString()
	public StringBuffer concatTree(Node R, StringBuffer s){
		if( R!=null ){
			concatTree(R.left, s);
			if( s.length()==0 ) s.append(R.key + " " + R.value + "\n");
			else {
				s.append(R.key + " " + R.value + "\n");
			}
			concatTree(R.right, s);
		}
		return s;
		}
	
	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	private void deleteAll(Node N){
	   if( N!=null ){
	      deleteAll(N.left);
	      deleteAll(N.right);
	   }
	}
	
	// public functions 

	//-----------------------------------------------------------
	// isEmpty()
	// pre: none
	// returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty() {
		return (numPairs == 0);
	}

	// size()
	// pre: none
	// returns the number of entries in this Dictionary
	public int size(){
		   return numPairs;
	}

	// lookup()
	// pre: none
	// returns value associated key, or null reference if no such key exists
	public String lookup(String key){
		Node R = root;
		if( findKey(R, key)==null ) return null;
		else {
			return findKey(R, key).value;
		}
	}

	// insert()
	// inserts new (key,value) pair into this Dictionary
	// pre: lookup(key)==null
	public void insert(String key, String value) throws DuplicateKeyException{
		Node N = new Node(key, value);
		Node A = root;
		Node B = null;
		if( findKey(A, key)!=null ) 
			throw new DuplicateKeyException
			("Dictionary Error: cannot insert() duplicate key: " + key);
		while( A!=null ) {
			B=A;
			if( key.compareTo(A.key)<0 ) A=A.left;
			else { A=A.right; }
		}
		if( B==null ) root=N;
		else if( key.compareTo(B.key)<0 ) B.left=N;
		else { B.right=N; }
		numPairs++;
	}

	// delete()
	// deletes pair with the given key
	// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException{
		Node N, P, S;
		if( findKey(root, key) == null ) 
			throw new KeyNotFoundException
			("Dictionary Error: cannot delete() non-existent key: " + key);
		N = findKey(root, key);
		if( N.left==null && N.right==null ) {  // case 1
			if( N==root ) {
				root = null;
			}
			else {
				P = findParent(N, root);
				if( P.right==N ) P.right = null;
				else P.left = null;
			}
		}
		else if( N.right==null ) {  // case 2 (left but no right child)
			if( N==root ) {
				root = N.left;
			}
			else {
				P = findParent(N, root);
				if( P.right==N ) P.right = N.left;
				else P.left = N.left;
			}
		}
		else if( N.left==null ) {  // case 2 (right but no left child)
			if( N==root ) {
				root = N.right;
			}
			else {
				P = findParent(N, root);
				if( P.right==N ) P.right = N.right;
				else P.left = N.right;
			}
		}
		else {                     // case3: N->left!=NULL && N->right!=NULL
			S = findLeftMost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);
			if( P.right==S ) P.right = S.right;
			else P.left = S.right;
		}
		numPairs--;
	}
	
	// makeEmpty()
	// pre: none
	public void makeEmpty() {
		deleteAll(root);
		root = null;
		numPairs = 0;
	}
	
	// toString()
	// returns a String representation of this Dictionary
	// overrides Object's toString() method
	// pre: none
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer("");
		return (concatTree(root, s).toString());
	}
		
}

