/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 7/28/2015
  File Description: Dictionary ADT contains private
  and public functions
  File Name: Dictionary.c
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// Private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
   	N->key = k;
   	N->value = v;
   	N->next = NULL;
   	return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN) {
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
        	*pN = NULL;
        }
}

// DictionaryObj
typedef struct DictionaryObj {
	Node head;
	int numItems;
} DictionaryObj;

// Dictionary
typedef DictionaryObj* Dictionary;


// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void) {
	Dictionary D = malloc(sizeof(DictionaryObj));
   	assert(D!=NULL);
   	D->head = NULL;
   	D->numItems = 0;
   	return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
	if( pD!=NULL && *pD!=NULL ){
      		if( !isEmpty(*pD) ) makeEmpty(*pD);
      		free(*pD);
     		*pD = NULL;
   	}
}

// Private helper function 
Node findTail(Dictionary D) {
	Node N = D->head;
	while( N->next!=NULL ) {
		N=N->next;
	}
	return N;
}

// Private helper function
// finds the Node before the Node with char* k
Node findKeyMinusOne(Dictionary D, char* k) {
	Node N = D->head;
	int i, j;
	for (i = 0; i < D->numItems; i++) {
		if(!strcmp(N->key, k)) break;
			N=N->next;	
	}
	if( i==0 ) return NULL;
	else {
		N = D->head;
		for (j = 0; j < i-1; j++) {
			N=N->next;
		}
		return N;
	}
}
	
//Private helper function to find Node with key==arg k
Node findKey(Dictionary D, char* k){
	Node N = D->head;
	int i;
	for( i=0; i < D->numItems; i++) {
		if( !strcmp(N->key, k) ) break;
		if( i==(D->numItems-1)) return NULL;
		N=N->next;
	}
	return N;
}

//-------------------------------------------------------------
//"Public" (Dictionary.h) functions
//-------------------------------------------------------------

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
	return (D->numItems==0);
}

// size()
// pre: none
// returns the number of entries in this Dictionary
int size(Dictionary D) {
	return D->numItems;
}
	
// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
	if( findKey(D, k)==NULL ) return NULL;
	return findKey(D, k)->value;	
	
}
   
// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
	Node N;

   	if( D==NULL ) {
      		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
   		exit(EXIT_FAILURE);
   	}
	if(lookup(D, k)!=NULL) {
		fprintf(stderr, "Duplicate Key Error: calling insert() on pre-existing key reference\n");
		exit(EXIT_FAILURE);
	}
   	N = newNode(k, v);
   	N->next = D->head;
  	D->head = N;
   	D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {
	Node N, C;
	Node P = findKey(D, k);

	if( lookup(D, k)==NULL ) {
		fprintf(stderr, "Dictionary Error: calling delete() on NULL key reference\n");
   		exit(EXIT_FAILURE);
   	}
	if( D->numItems==0 ){
      		fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary\n");
   		exit(EXIT_FAILURE);
   	}
	if( D->numItems==1 ) {
		N = D->head;
		D->head = NULL;
		freeNode(&D->head);
		D->numItems--;
	}
	else if( P==D->head ) { 
		N = D->head;
		D->head = D->head->next;
		freeNode(&N);
		D->numItems--;
	}
	else if( P->next==NULL ) {
		N = P;
		C = findKeyMinusOne(D, k);
		C->next = NULL;	
		freeNode(&N);
		D->numItems--;
	}
	else {
		C = findKeyMinusOne(D, k);
	        N = P->next;
	        C->next = N;
	        freeNode(&P);
		D->numItems--;
	}

}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {
	Node N, C;
	N = D->head;

	if( D==NULL ) {
      		fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
      		exit(EXIT_FAILURE);
	}
	if( D->numItems==0 ) {
		exit(EXIT_SUCCESS);
	}
	N=findTail(D);
	
	//set N equal to the Node before the tail
	//make each Node->next null, loop ends with N=D->head
	while( D->numItems > 1 ) {
		N = findKeyMinusOne(D, N->key);
		C=N->next;
		N->next=NULL;
		freeNode(&C);
		D->numItems--;
	}
	D->head=NULL;
	freeNode(&N);
	D->numItems--;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D) {
	Node N;
   	if( D==NULL ) {
      		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      		exit(EXIT_FAILURE);
   	}
	if( D->numItems==0 ) {
		fprintf(out, "\n");
		exit(EXIT_SUCCESS);
 	}
   	for( N=findTail(D); N!=NULL; N=findKeyMinusOne(D, N->key)) fprintf(out, "%s %s\n", N->key, N->value);
}
