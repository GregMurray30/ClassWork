/* 
  Student Name: Greg Murray
  SID: 1101589
  Class: CS12M
  Date: 8/11/2015
  File Description: Hash table ADT contains private
  and public functions. Based on an array of linked
  lists.
  File Name: Dictionary.c
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

//--------------------------------------
// Hash functions


const int tableSize = 101;


// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}


//------------------------------------------------
// private types and functions


// NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
    Node N = calloc(1, sizeof(NodeObj));
    assert(N!=NULL);
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node* table;
    int numPairs;
} DictionaryObj;

// Private helper function
Node findTail(Dictionary D, int i) {
    Node N = D->table[i];
    while( N->next!=NULL ) {
        N=N->next;
    }
    return N;
}
// Private helper function returns the Node with
// key k.
Node findKey(Dictionary D, char* k) {
    int x = 0;
    int i = hash(k);
    Node C, H;
    H = D->table[i];
    C = H;
    while( C!=NULL ) {
        x++;
        if( strcmp(k, C->key)==0 ) break;
        if( C->next==NULL ) return NULL;
        C=C->next;
    }
    return C;
}

// Private helper function returns the Node with
// key k.
Node findKeyMinusOne(Dictionary D, char* k) {
    int x = 0;
    int y;
    int i = hash(k);
    Node C, head;
    head = D->table[i];
    C = head;
    while( C!=NULL ) {
        if( strcmp(k, C->key)==0 ) break;
        if( C->next==NULL ) return NULL;
        x++;
        C=C->next;
    }
    printf("x:%d\n", x);
    if( x==0 ) return NULL;
    else {
        C=head;
        for ( y = 0; y < x-1; y++) {
            C = C->next;
        }
        return C;
    }
}

//----------------------------------------
// Public functions


// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
    Dictionary D = calloc(1, sizeof(DictionaryObj));
    D->table = (Node*) calloc(tableSize, sizeof(Node));
    assert(D!=NULL);
   // for( i=0; i<180; i++ ) D->table[i] = NULL;
    D->numPairs = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
}


// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
    return (D->numPairs==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
    return D->numPairs;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
    if( findKey(D, k)==NULL ) return  NULL;
    return findKey(D, k)->value;

}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D,char* k, char* v) {
    int i = hash(k);
    Node H = NULL;
    Node N = newNode(k, v);
    if( D==NULL ) {
        fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if(findKey(D, k)!=NULL) {
        fprintf(stderr, "Duplicate Key Error: calling insert() on pre-existing key reference %s\n", k);
        exit(EXIT_FAILURE);
    }
    if( D->table[i]==NULL ) {
        D->table[i] = N;
        D->numPairs++;
    }
    else {
        H = D->table[i];
        while( H->next!=NULL ) {
           // if( H->next==NULL ) break;
            H=H->next;
        }
        H->next = N;
        D->numPairs++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {
    int i = hash(k);
    Node  N, C;
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( findKey(D, k)==NULL ) {
        fprintf(stderr, "Dictionary Error: calling delete() on non-existent key: %s\n", k);
        exit(EXIT_FAILURE);
    }
    if( D->numPairs==0 ){
        fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary\n");
        exit(EXIT_FAILURE);
    }
    if( D->table[i]->next==NULL ) {
        N = D->table[i];
       // D->table[i] = NULL;
        freeNode(&N);
        D->table[i]=NULL;
        D->numPairs--;
    }
    else if(findKey(D,k)==D->table[i] ) {
        N = D->table[i];
        D->table[i] = D->table[i]->next;
        freeNode(&N);
        D->numPairs--;
    }
    else if( findKey(D,k)->next==NULL ) {
        C = findKeyMinusOne(D, k);
        C->next = NULL;
        freeNode(&N);
        D->numPairs--;
    }
    else {
        C = findKeyMinusOne(D, k);
        N = findKey(D,k);
        C->next = N->next;
        freeNode(&N);
        D->numPairs--;
    }

}
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {
    int i;
    Node N, C;
    if( D==NULL ) {
        fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( D->numPairs==0 ) {
        exit(EXIT_SUCCESS);
    }
    for( i=0; i<tableSize; i++ ) {
        if( D->table[i]!=NULL ) {
            N = findTail(D, i);
           // printf("N(tail) value:%s\n", lookup(D,C->key));
            while( D->table[i]->next!=NULL ) {
                N = findKeyMinusOne(D, N->key);
                C = N->next;
                N->next = NULL;
                freeNode(&C);
                D->numPairs--;
            }
            freeNode(&N);
            D->table[i] = NULL;
            D->numPairs--;
        }
    }
    //free(&D->table);
    //D->table = NULL;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    int i;
    Node N;
    if( D==NULL ) {
        fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( D->numPairs==0 ) {
        fprintf(out, "\n");
        exit(EXIT_SUCCESS);
    }
    for( i=0; i<tableSize; i++ ) {
        if( D->table[i]!=NULL ) {
            for (N = findTail(D, i); N!=NULL; N = findKeyMinusOne(D, N->key))
                 fprintf(out, "%s %s\n", N->key, N->value);
        }
    }
}
