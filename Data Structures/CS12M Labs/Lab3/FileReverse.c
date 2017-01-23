/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 7/13/2015
  File Description: File FileReverse.c that copies the Strings
  in a source file (passed as first command line argument), parses
  the Strings, reverses them and prints each token on a line in 
  a target file (passed as second command line argument).
  File Name: FileReverse.c
*/


#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/* Iterative function that takes parameter
*  char* and reverses it
*/

void stringReverse(char* s) {
	int i, j = strlen(s)-1;
	char temp;
        for (i = 0; i < j; i++) {
		temp = s[i];
		s[i] = s[j];
		s[j] = temp;
		j--;
	}
}

int main(int argc, char* argv[]){
 FILE* in; /* file handle for input */
 FILE* out; /* file handle for output */
 char word[256]; /* char array to store words from input file */
 /* check command line for correct number of arguments */

 if( argc != 3 ){
 printf("Usage: %s <input file> <output file>\n", argv[0]);
 exit(EXIT_FAILURE);
 }
 /* open input file for reading */
 in = fopen(argv[1], "r");
 if( in==NULL ){
 printf("Unable to read from file %s\n", argv[1]);
 exit(EXIT_FAILURE);
 }


 /* open output file for writing */
 out = fopen(argv[2], "w");
 if( out==NULL ){
 printf("Unable to write to file %s\n", argv[2]);
 exit(EXIT_FAILURE);
 }

 /* read words from input file, print on separate lines to output file*/
 while( fscanf(in, " %s", word) != EOF ){
 stringReverse(word);
 fprintf(out, "%s\n", word);
 }

 /* close input and output files */
 fclose(in);
 fclose(out);
 return(EXIT_SUCCESS);
}
