#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char* diff(char* A, char* B) {
	int i, j, index = 0;
	char* differences = malloc(strlen(A)*sizeof(int));
	for( i=0; i < strlen(A); i++) {
		for( j=0; j < strlen(B); j++) {
			printf("differences: %s\n", differences);
			printf("(in j) i= %d, j= %d\n", i, j);
			if( *(A+i)==(*(B+j)) ) break;
		}
		printf("(out of j) i= %d, j= %d\n", i, j);
		if( j==4 ) {
			differences[index] = *(A+i);
			index++;
		}
	}
	//printf("(out of both) differences: %s\n", differences);
	return differences;
}

int main(void){
	char* A = "abcd";
	char* B = "bgdv";
	printf( "%s\n", diff(A, B) );
}
