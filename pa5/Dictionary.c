//-----------------------------------------------------------------------------
//  Spencer Albrecht
//  salbrech
//  PA 5
//  Dictionary.c
//  Implementation file for Dictionary ADT using a hash table
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
#define TABLE_SIZE 101

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 ) {
        return value;
    }
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
// turns a string into an int in the range 0 to TABLE_SIZE-1
int hash(char* key){
    return pre_hash(key)%TABLE_SIZE;
}

// Creation of NodeObj data type
typedef struct NodeObj{
    char key[150];
    char value[150];
    struct NodeObj* next;
    struct NodeObj* last;
} NodeObj;

// Creation of type "Node" that points to NodeObj
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* key, char* value) {
    Node node  = malloc(sizeof(NodeObj));
    if(node==NULL) {
        fprintf(stderr,
            "malloc() failed when trying to allocate for a newNode\n");
        exit(EXIT_FAILURE);
    }
    strcpy(node->key, key);
    strcpy(node->value, value);
    node->next = NULL;
    node->last = NULL;
    return(node);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
    }
}

// Creation of the DictionaryObj data type
typedef struct DictionaryObj{
    int numItems;
    Node array[TABLE_SIZE];
} DictionaryObj;

// Creation of type "Dictionary" that points to DictionaryObj
typedef DictionaryObj* Dictionary;

// findKey
// returns the Node associated with the key or returns null if no
// such key k exists.
// pre: none
Node findKey(Dictionary dict, char* key) {
    // If the dict is null or is empty return null
    if (dict!=NULL && !isEmpty(dict)) {

        int index = hash(key);
        // printf("the index for key: %s is %d\n", key, index);
        // printf("line 99\n");
        // Case if there are no collisions and the node is the only
        // one at that array index
        if(dict->array[index]==NULL) {
            // printf("line 103\n");
            return NULL;
        }
        // Case if chaining is used to avoid collisions and the linked
        // list at that index needs to be searched
        else {
            Node finder = dict->array[index];
            // printf("line 110\n");
            while(finder!=NULL) {
                // Check the key on each node and compary to target.
                // Return Node object if found
                if (strcmp(finder->key, key)==0) {
                    // printf("line 115\n");
                    return finder;
                }
                else {
                    // printf("line 119\n");
                    finder = finder->next;
                }
            }
        }
    }
    // Only return null if the key was not found
    return NULL;
}

// newDictionary()
// Constructor of the Dictionary Type
Dictionary newDictionary(void) {
  Dictionary dict = malloc(sizeof(DictionaryObj));
  if(dict==NULL) {
    fprintf(stderr,
      "malloc() failed when trying to allocate for a newDictionary\n");
      exit(EXIT_FAILURE);
    }
    dict->numItems = 0;
    return dict;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
  if( pD!=NULL && *pD!=NULL ){
    if(!isEmpty(*pD)) {
      makeEmpty(*pD);
    }
    free(*pD);
    *pD = NULL;
  }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
  if( D==NULL ){
    fprintf(stderr,
      "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
    return(D->numItems==0);
  }

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
  if( D==NULL ){
    fprintf(stderr,
      "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
    return(D->numItems);
  }

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
    if( D==NULL ){
        fprintf(stderr,
            "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    // Check if numItems = 0 to avoid calling findKey if not needed
    if (!isEmpty(D)) {
        // printf("line 188\n");
        Node returnNode = findKey(D,k);
        if (returnNode!=NULL) {
            // printf("line 190\n");
            return returnNode->value;
        }
    }
    // Returns null if Dictionary is empty
    // printf("line 197\n");
    return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
  // printf("line 204\n");
  if( D==NULL ){
    fprintf(stderr,
      "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
  }
  // printf("line 209\n");
  // Check if a Node with that key is already in the list,
  // if it is, print error message and exit
  if (lookup(D,k)!=NULL) {
    fprintf(stderr,
      "Dictionary Error: calling insert() with duplicate keys\n");
      exit(EXIT_FAILURE);
  }
  else {

    int index = hash(k);

    // Case if there are no collisions and the node is the only
    // one at that array index
    if(D->array[index]==NULL) {
        Node temp = newNode(k,v);
        D->array[index] = temp;
        D->numItems++;
    }
    // Case if chaining is used to avoid collisions and the linked
    // list at that index needs to be searched
    else {
        Node temp = newNode(k,v);
        // Set the current head to the new node's next
        temp->next = D->array[index];
        // Set the current head's last to the new node
        D->array[index]->last = temp;
        D->array[index] = temp;
        D->numItems++;
    }
  }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
  if( D==NULL ){
    fprintf(stderr,
      "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
  }
  // Prints error and exits if the key we are trying to delete is
  // not in the list
  if (lookup(D,k)==NULL) {
    fprintf(stderr,
      "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
  }
  else {
    // Create a new Node a set it equal to the Node we want to delete
    Node tempNode = findKey(D,k);
    int index = hash(k);
    Node tracer = D->array[index];

    // Loop that runs through link list at appropriate index
    while(tracer!=NULL) {
        if (tracer == tempNode) {
            // Case if we are deleting the first Node
            if (D->array[index]==tracer) {
                D->array[index]=NULL;
                // printf("line 269\n");
            }
            // Case if we are deleting the last Node
            else if (tracer->next==NULL) {
                tracer->last->next = NULL;
                freeNode(&tracer);
                // printf("line 277\n");
            }
            // Case if we are deleting a middle Node
            else {
                tracer->last->next = tracer->next;
                tracer->next->last = tracer->last;
                freeNode(&tracer);
                // printf("line 282\n");
            }
        }
        tracer = tracer->next;
    }
    D->numItems--;
  }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
            "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
            exit(EXIT_FAILURE);
        }
        for (int i = 0; i < TABLE_SIZE; i++) {
            Node tracer = D->array[i];
            Node tempHead = D->array[i];
            while(tracer!=NULL) {
                tempHead = tempHead->next;
                freeNode(&tracer);
                tracer = tempHead;
            }
            D->numItems = 0;
        }
    }

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
  if( D==NULL ){
    fprintf(stderr,
      "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
    for (int i = 0; i < TABLE_SIZE; i++) {
        Node tracer = D->array[i];
        while(tracer!=NULL) {
            fprintf(out, "%s %s\n", tracer->key, tracer->value);
            tracer = tracer->next;
        }
    }
  }
