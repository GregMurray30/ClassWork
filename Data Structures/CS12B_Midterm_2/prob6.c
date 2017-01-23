#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int length(char* S) {
	return strlen(S);
}

int search( char* S, char c) {
	int i;
	for( i=0; i<strlen(S); i++) {
		if( (*(S+i))==c ) return i;
	}
	return -1;
}

int main(void) {
	char* S = "abc";
	char c = 'c';
	printf("%d\n", search(S, c));
}
