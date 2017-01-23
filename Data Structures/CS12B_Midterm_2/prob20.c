#include<stdio.h>
#include<stdlib.h>

void printArray(int* A, int n){
   int i;
   for(i=0; i<n; i++){
      printf("%d ", A[i]);
   }
   printf("\n");
}

int* concate(int * A, int n, int* B, int m) {
	int i, j;
	int p = m+n;
	int* q;
	int* newArray = calloc(p, sizeof(int));
	for( i=0; i<n; i++) {
		newArray[i] = A[i];
	}
	for( j=0; j<p; j++) {
		newArray[n+j] = B[j];
}
	return newArray;
}

int main(void) {
	int* A = {1, 2, 3};
	int* B = {4, 5, 6};
	printArray(concate(A, 3, B, 3), 6);
	return 0;
}
