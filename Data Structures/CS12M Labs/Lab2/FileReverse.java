/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 7/6/2015
  File Description: A java file that copies the Strings
  in a source file (passed as first command line argument), parses
  the Strings, reverses them and prints each token on a line in 
  a target file (passed as second command line argument).
  File Name: FileReverse.java
*/


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

// Gnirts class (String spelled backwards) was created
// to circumvent the issue of resetting StringBuffer tmp
// after every token reversal
class Gnirts {
	
	// temp class variable for stringReverse()
	static StringBuffer tmp = new StringBuffer(); 
	
	public String value;
	
	// constructs the Gnirt instance as the reverse
	// of the String argument it is passed
	Gnirts(String g) {
		g = stringReverse(g, g.length());
		value = g;
	}
	
	// recursive method for lab2
	public static String stringReverse(String s, int n) {
		StringBuffer sbf = new StringBuffer(s);
		if (n > 0){
			tmp.append(sbf.charAt(n-1));
		    stringReverse(s, n-1);
		}
		String output;
		return output = tmp.toString();
	}
  }

public class FileReverse {
	
	public static void main (String[] args) throws IOException{
		
		Scanner in = null;
		PrintWriter out = null;
		String[] token = null;
		String line = null;
		int n;
		
		// check command line arguments
		if(args.length < 2){
			System.out.println("Usage: FileTokens infile outfile");
			System.exit(1);
		}
		
		// open files
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		
		// read  from in, extract and print tokens from each line
		while( in.hasNextLine() ){
			
			// trim leading and trailing spaces, then add one trailing space so
			// split works on blank lines
			line = in.nextLine().trim() + " ";
			
			token = line.split("\\s+");
			
			n = token.length;
			
			// iterate through the token array,
			// reverse the string at each index,
			// and print it to File out
			for (int i = 0; i < n; i++) {
				Gnirts g = new Gnirts(token[i]);
				out.println(g.value);
				
				// reset Gnirt instance's StringBuffer "tmp"
				Gnirts.tmp.delete(0, Gnirts.tmp.length());
			}
			
		}
			in.close();
			out.close();
	}
}
