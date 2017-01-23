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

import java.io.*;
import java.util.Scanner;

class Target {
	String value = "";
	public Target() {
	}
}

public class Search {
	static String[] lineIndex;

   // mergeSort() with String Arrays
   // sort subarray A[p...r]
   public static void mergeSort(String[] A, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(A, p, q);
         mergeSort(A, q+1, r);
         merge(A, p, q, r);
      }
   }
   
   // merge() with String Arrays+
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] A, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int i, j, k;

      for(i=0; i<n1; i++) L[i] = A[p+i];
      for(j=0; j<n2; j++) R[j] = A[q+j+1];
      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j])<0) {
               A[k] = L[i];
               i++;
            }else{
               A[k] = R[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
            j++;
         }
      }
   }	

	// binarySearch() with String Arrays
	// pre: Array A[p..r] is sorted
   	public static int binarySearch(String[] A, int p, int r,  String target){
   		int q;
   		if(p > r) {
   			return -1;
   		}
   		else {
   			q = (p+r)/2;
   			if(target.compareTo(A[q])==0){
   				return q;
   			}
   			else if(target.compareTo(A[q])<0){
   				return binarySearch(A, p, q-1, target);
   			}
   			else{ // target > A[q]
   				return binarySearch(A, q+1, r, target);
   			}
   		}
   	}
		   
   	// inputReader method captures the content in the input source file
    // returns a String[] with each line from input as a separate element
   	public static String[] inputReader(Scanner input) throws IOException {
	   	
   		// count the number of lines in file
		input.useDelimiter("\\Z"); // matches the end of file character
		String s = input.next();  // read in whole file as a single String
		input.close();
		String[] lines = s.split("\n");  // split s into individual lines
		//int lineCount = lines.length;  // extract length of the resulting array
		//return lineCount;
		return lines;
	   }
		   
   	public static void main(String[] args) throws IOException {
   		// number of targets to search
   		int n = (args.length-1);
   		
   		// create an Array of abstract object Target of length n
   		Target[] target = new Target[n];
   		
   		// validate number of command line arguments
   		if(args.length < 2 ){
   			System.err.println("Usage: Search file target1 [target 2...]");
   			System.exit(1);
   		}
   		
   		// validate args[0] is a 1.) a file, and 2.) can be found
   		try {
   			// serves only to ensure args[0] is a file that
   			// can be found
   			Scanner input = new Scanner(new File(args[0]));
   			input.close();
   		}
   			catch (java.io.FileNotFoundException e) {
   				System.err.println("Usage: Search file target1 [target 2...]");
   	   			System.exit(1);
   			}
   		
   		// read input file
   		Scanner input = new Scanner(new File(args[0]));
   		
   		// save variable number of target arguments in a Target[] 
   		// and assigning their class value as the corresponding arg
   		for (int i = 0; i < n; i++) {
   			Target targetTemp = new Target();
   			target[i] = targetTemp;
   			target[i].value = args[i+1];
   		}
   
   		// "initiate" class String[], lineIndex, with each line from
   		// File input as the elements
   		lineIndex = inputReader(input);
   		
   		// clone lineIndex to preserve the original value indexes
   		// by sorting/searching on the cloned Array lineField
   		String[] lineField = lineIndex.clone();
   		
   		// sort lineField
   		mergeSort(lineField, 0, lineField.length-1);
   		
   		int x = lineIndex.length;
   		int counter;
   		
   		// print whether a target is found and its original index if applicable
   		
   		for (int i = 0; i < n; i++) { // variable n is the # of targets
   			counter = 0; // count to determine index
   			for (int j = 0; j < x; j++) {
   				//compare target's value to the value of each element in lineIndex
   				if (lineIndex[j].equals(target[i].value)) {
   					counter++;
   					break;
   				}
   				counter++;
   			}
   			if ((binarySearch(lineField, 0, lineField.length, target[i].value))==-1) {
   				System.out.println(target[i].value + " not found");
   			}
   			else {
   				System.out.println(target[i].value + " found on line " + (counter));
   			}
   		}
   		// close file
   		input.close();
   	}
}
