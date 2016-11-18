//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// List.java
// Lab 6
// Linked List implementation of the List-of-Anything ADT
// using Java generics
//-----------------------------------------------------------------------------

@SuppressWarnings("Overrides")
public class List<T> implements ListInterface<T> {

   // private inner Node class
   private class Node<T> {
      T item;
      Node<T> next;

      Node(T x){
         item = x;
         next = null;
      }
   }

   // Fields for the List class
   private Node<T> head;     // reference to first Node in List
   private int numItems;  // number of items in this IntegerList

   // List()
   // constructor for the List class
   public List(){
      head = null;
      numItems = 0;
   }


   // private helper function -------------------------------------------------

   // find()
   // returns a reference to the Node at position index in this List
   private Node<T> find(int index){
      Node<T> tracer = head;
      for(int i=1; i<index; i++){
         tracer = tracer.next;
      }
      return tracer;
   }


   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this List is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this List
   public int size() {
      return numItems;
   }

   // get()
   // pre: 1 <= index <= size()
   // post: returns item at position index in this List
   public T get(int index) throws ListIndexOutOfBoundsException {

      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "get() called on invalid index: " + index);
      }
      Node<T> tempNode = find(index);
      return tempNode.item;
   }

   // add()
   // inserts newItem into this List at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem)
      throws ListIndexOutOfBoundsException{

      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "add() called on invalid index: " + index);
      }
      // case for inserting node at the head
      if(index==1){
         Node<T> tempNode = new Node<T>(newItem);
         tempNode.next = head;
         head = tempNode;
      }else{
         Node<T> nodeBefore = find(index-1); // at this point index >= 2
         Node<T> nodeAfter = nodeBefore.next;
         Node<T> insertNode = new Node<T>(newItem);
         nodeBefore.next = insertNode;
         insertNode.next = nodeAfter;
      }
      numItems++;
   }

   // remove()
   // deletes item at position index from this List
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index)
      throws ListIndexOutOfBoundsException{
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "remove() called on invalid index: " + index);
      }
      // case for deleting the node if it's the head
      if(index==1){
         Node<T> tempNode = head;
         head = head.next;
         // destroys the link from the old node to the head
         tempNode.next = null;
      }else{
         Node<T> nodeBefore = find(index-1);
         // node after the node we are going to delete
         Node<T> tempNode = nodeBefore.next;
         nodeBefore.next = tempNode.next;
         tempNode.next = null;
      }
      numItems--;
   }

   // removeAll()
   // pre: none
   // post: isEmpty()
   public void removeAll(){
      head = null;
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node<T> N = head;

      for( ; N!=null; N=N.next){
         sb.append(N.item).append(" ");
      }
      return new String(sb);
   }

   // equals()
   // pre: none
   // post: returns true if the List<T>s are equal
   // Checks if an object is a List<T> and
   // has the same entries as this List<T>
   @SuppressWarnings("unchecked")
   public boolean equals(Object unknownObject){
      boolean equal = false;
      List<T> foreignList = null;
      Node<T> tracer = null;
      Node<T> unknownTracer = null;

      if(this.getClass()==unknownObject.getClass()){
         // because we checked the class of the unknownObject
         // we can safely cast it as a List<T>
         foreignList = (List<T>) unknownObject;
         equal = (this.numItems == foreignList.numItems);
         tracer = this.head;
         unknownTracer = foreignList.head;
         while(equal && tracer!=null){
            equal = ((tracer.item).equals(unknownTracer.item));
            tracer = tracer.next;
            unknownTracer = unknownTracer.next;
         }
      }
      return equal;
   }

}
