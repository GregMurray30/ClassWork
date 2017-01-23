#include<stdio.h>
#include<stdlib.h>

//-----------------------------------------------------------------------------
// Sort.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

void printArray(char** A, int n){
   int i;
   for(i=0; i<n; i++){
      printf("%s ", A[i]);
   }
   printf("\n");
}

void swap(char** A, int i, int j){
   char* temp;
   temp = A[i];
   A[i] = A[j];
   A[j] = temp;
}

void BubbleSort(int* A, int n){
   int i, j;
   for(j=n-1; j>0; j--){
      for(i=1; i<=j; i++){
         if( A[i]>A[i-1] ) swap(A, i, i-1);
      }
   }
}

void SelectionSort(char** A, int n){
   int i, j, imax;
   for(j=n-1; j>0; j--){
     // printArray(A, 3);
    //  printf("j: %d\n", j);
      imax = 0;
      for(i=1; i<=j; i++) {
	 //printf("i: %d, A[%d]=%d\n", i, i, A[i]);
         if( A[imax][0]<A[i][0] ) {
		imax = i;
		printf("(in if) imax: %d, i: %d\n", imax, i); 
 	 }
      }
      swap(A, imax, j);
      
   }
}

void InsertionSort(char** A, int n){
   int i, j;
   char* temp;
   for(j=1; j<n; j++){
      temp = A[j];
      i = j-1;
      while( i>=0 && temp[0]<A[i][0] ){
         A[i+1] = A[i];
         i--;
      }
      A[i+1] = temp;
   }
}





int main(void) {
   char* A[] = {"g","d","a"};
   //InsertionSort(A, 3);
   SelectionSort(A, 3);
   printArray(A, 3);
   return(EXIT_SUCCESS);
}
