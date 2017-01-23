/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 7/28/2015
  File Description: A test file with main to
  test functions in the Dictionary ADT
  File Name: DictionaryTest.c
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   	Dictionary A = newDictionary();

	//TEST isEmpty(), size(), insert()
	//printf("%d\n", isEmpty(A));
	//printf("%d\n", size(A));
	insert(A, "one", "test1");
	insert(A, "two", "test2");
	insert(A, "three", "test3");
	//insert(A, "one", "test1");
	//printf("%d\n", isEmpty(A));
	//printf("%d\n", size(A));

	/*TEST findKeyMinusOne(), findTail()
	  Functions lookupBefore(Dictionary D, char* k) and 
	  lookupTail(Dictionary D) were temporarily added to
	  Dictionary.h to test these helper functions
	*/
	//printf("%s\n", lookupBefore(A, "three"));
	//printf("%s\n", lookupBefore(A, "one"));
	//printf("%s\n", lookupTail(A));

	//TEST lookup(), delete(), printDictionary()
	//printf("%s\n", lookup(A, "one"));
	//printf("%s\n", lookup(A, "four"));
	//delete(A, "one");
	//printf("%d\n", size(A));
	//delete(A, "two");
	//delete(A, "three);
	//printDictionary(stdout, A);
	
	//TEST makeEmpty()
	//insert(A, "two", "test2");
	//makeEmpty(A);
	//printf("%d\n", isEmpty(A));
	//printf("%d\n", size(A));
	printDictionary(stdout, A);

   	freeDictionary(&A);
	
   	return(EXIT_SUCCESS);
}
