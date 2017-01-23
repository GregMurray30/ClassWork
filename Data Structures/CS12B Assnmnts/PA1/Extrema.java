/*
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12B
  Date: 7/1/2015
  File Description: This java file Extrema.java consists of one class, Extrema,
  and 3 methods including main(). The other two methods are maxArray and       minArray which take 3 arguments (including an int Array) and return the int     value of the biggest int in the Array and smallest int in the Array,          respectively. See comments in the body of the program for functionality 
  information.
  File Name: Extrema.java
*/

public class Extrema {
	
    //Simple max function
    static int max(int a, int b){
    	if (a>b) return a;
    	else return b;
    }
    
    //Simple minimum function
    static int min(int a, int b){
    	if (a<b) return a;
    	else return b;
    }

        //Algorithm isolates single integers in the Array recursively
        //by splitting the Array in half until the base case is reached.
	static int maxArray(int[] A, int p, int r) {
	int q = (p+r)/2; //Variable q is the midpoint of the Array A
    	if (p == r) {//Base case: when p==r the Array is of size 1.
    		return A[q];
    	}
    	//If not base case, then return the maximum integer between the
    	//subArray from the left half and the subArray from the right half.
    	return max(maxArray(A, p, q), maxArray(A, q+1, r));
    }
	
	//Identical to maxArray except that it calls the method min() to find 
	//the smallest integer in the Array
	static int minArray(int[] A, int p, int r) {
		int q = (p+r)/2;
    	if (p == r) {
    		return A[q];
    	}
    	return min(minArray(A, p, q), minArray(A, q+1, r));
    }
	
	 public static void main(String[] args){
		 int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7};
		 System.out.println( "max = " + maxArray(B, 0, B.length-1) );
		 System.out.println( "min = " + minArray(B, 0, B.length-1) );
		 }

}
