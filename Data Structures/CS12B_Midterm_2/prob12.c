#include<stdio.h>
#include<stdlib.h>

int countComparisons(int* A, int n, int i) {
	int elements=0, h, j;
	int target = A[i];
	for( h=0; h<i; h++) 
		if( A[h]<=target ) elements++;
	for( j=i+1; j<n; j++)
		if( A[j]<=target ) elements++;
	return elements;
}

int main(void) {
	int A[] = {1, 2, 3, 3, 4, 5};
	printf("%d\n", countComparisons(A, 6, 2));
	return 0;
}