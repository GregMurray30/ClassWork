#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char* diff(char* A, char* B) {
	int i, j, x = 0;
	char* diff = (char*) malloc(strlen(A)*sizeof(char));
	for( i=0; i < strlen(A); i++) {
		for( j=0; j < strlen(B); j++) {
			if( A[i]==B[j] ) break;
		}
		if( j==(strlen(B)-1) ) {
			diff[x] = A[i];
			x++;
		}
	}
	return diff;
}

int main(void){
	char* A = "abcd";
	char* B = "bgdv";
	printf( "%s\n", diff(A, B) );
}
