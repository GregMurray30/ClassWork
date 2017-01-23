
/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 7/12/2015
  File Description: Prints the number of alphabetic,
  digit, punctuation, and whitespace characters in 
  the input argument from the command line to the output
  file.
  File Name: charType.c
*/

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   int lineCounter = 0;
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alphabetic; // string holding all alphabetic chars 
   char* digits; // string holding all digit chars 
   char* punctuation; // string holding all punctuation chars 
   char* whitespace; // string holding all whitespace chars 

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alphabetic = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digits = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alphabetic!=NULL && digits!=NULL && punctuation!=NULL && whitespace!=NULL);

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      lineCounter++;
      extract_chars(line, alphabetic, digits, punctuation, whitespace);
      fprintf(out, "Line %d contains:\n", lineCounter);
      if((int)strlen(alphabetic)==1)
           fprintf(out, "%ld alphabetic character: %s\n", strlen(alphabetic), alphabetic);
      else {
           fprintf(out, "%ld alphabetic characters: %s\n", strlen(alphabetic), alphabetic);
      }
      if((int)strlen(alphabetic)==1)
           fprintf(out, "%ld digit character: %s\n", strlen(digits), digits);
      else {
           fprintf(out, "%ld digit characters: %s\n", strlen(digits), digits);
      }
      if((int)strlen(alphabetic)==1)
           fprintf(out, "%ld punctuation character: %s\n", strlen(punctuation), punctuation);
      else {
           fprintf(out, "%ld punctuation characters: %s\n", strlen(punctuation), punctuation);
      }
      if((int)strlen(alphabetic)==1)
           fprintf(out, "%ld whitespace character: %s\n", strlen(whitespace), whitespace);
      else {
           fprintf(out, "%ld whitespace characters: %s\n", strlen(whitespace), whitespace);
      }
   }

   // free heap memory 
   free(line);
   free(alphabetic);
   free(digits);
   free(punctuation);
   free(whitespace);
   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0,  aL = 0, dL = 0, pL = 0, wL = 0; // each char array's size
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( s[i]) ) a[aL++] = s[i];
      else if( isdigit( s[i]) ) d[dL++] = s[i];
      else if( ispunct( s[i]) ) p[pL++] = s[i];
      else if( isspace( s[i]) ) w[wL++] = s[i];
      i++;
   }
   a[aL] = '\0';
   d[dL] = '\0';
   p[pL] = '\0';
   w[wL] = '\0';

  
}