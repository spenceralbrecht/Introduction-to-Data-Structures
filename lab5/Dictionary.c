//-----------------------------------------------------------------------------
//  Spencer Albrecht
//  salbrech
//  Lab5
//  Dictionary.c
//  Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// Creation of NodeObj data type
typedef struct NodeObj{
   char key[50];
   char value[50];
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
   node->last = NULL;
   node->next = NULL;
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
   Node head;
   Node tail;
} DictionaryObj;

// Creation of type "Dictionary" that points to DictionaryObj
typedef DictionaryObj* Dictionary;

// findKey
// returns the Node associated with the key or returns null
// such key k exists.
// pre: none
Node findKey(Dictionary dict, char* key) {
   // If the dict is null or is empty return null
   if (dict!=NULL && !isEmpty(dict)) {
      // Create a new node that will travel through the linked
      // list checking for matches
      Node finder = dict->head;
      // Loop through each node until the end of the list is reached
      while(finder!=NULL) {
         // Check the key on each node and compary to target.
         // Return Node object if found
         if (strcmp(finder->key, key)==0) {
            return finder;
         }
         else {
            finder = finder->next;
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
   dict->head = NULL;
   dict->tail = NULL;
   return dict;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
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
     Node returnNode = findKey(D,k);
     if (returnNode!=NULL) {
      return returnNode->value;
     }
   }
   // Returns null if Node was not found in list or if numItems = 0
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
    if( D==NULL ){
      fprintf(stderr,
              "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   // Check if a Node with that key is already in the list,
   // if it is, print error message and exit
   if (lookup(D,k)!=NULL) {
      fprintf(stderr,
             "Dictionary Error: calling insert() on NULL Dictionary reference\n");
     exit(EXIT_FAILURE);
   }
   else {
     Node node = newNode(k, v);
     // Properly insert at head of current linked list
     if (!isEmpty(D)) {
      node->next = D->head;
      D->head->last = node;
     }
     // If this is the first Node in the list, set next and
     // last appropriately
     else {
      node->next = NULL;
      D->tail = node;
     }
     // Because we are inserting at head, our new Node's .last field
     // will always be set to null
     node->last = NULL;
     D->head = node;
     D->numItems++;
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
      // Special case for if we are deleting the last Node in the list
      if (D->tail==tempNode) {
        D->tail = tempNode->last;
        tempNode->last->next = NULL;
      }
      // Special case for deleting the first Node in the list
      else if (D->head==tempNode) {
        D->head = tempNode->next;
        tempNode->next->last = NULL;
      }
      // General case for deleting a Node inside the list
      else {
        tempNode->last->next = tempNode->next;
        tempNode->next->last = tempNode->last;
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
   freeDictionary(&D);
   D->head = NULL;
   D->tail = NULL;
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
   Node temp = D->tail;
   while(temp!=NULL) {
      fprintf(out, "%s %s\n", temp->key, temp->value);
      temp = temp->last;
   }

}
