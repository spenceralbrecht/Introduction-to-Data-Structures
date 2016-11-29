// Spencer Albrecht
// salbrech
// Lab 7
// Dictionary.java
// All of the methods needed to implement a dictionary
// with key value pairs as a binary search tree
public class Dictionary implements DictionaryInterface {

   // Private inner Node class
   private class Node {
      // Fields that store the parameters that each node will contain
      String key;
      String value;
      Node left;
      Node right;

      // Constructor for node with a key and value
      Node(String key, String value){
         this.value = value;
         this.key = key;
         this.left = null;
         this.right = null;
      }

      // Constructor without parameters
      Node() {
         this.value = null;
         this.key = null;
         this.right = null;
         this.left = null;
      }
   }

   // Fields for the Dictionary class
   private Node root;     // reference to root in the Dictionary
   private int numPairs;  // number of pairs in current Dictionary

   // Constructor for a Dictionary object. Initialized with
   // default fields
   public Dictionary() {
      root = null;
      numPairs = 0;
   }

   // Private method that is used by lookup, insert, and delete
   // to find and return a node with a specific key
   private Node findKey(Node root, String key) {
      if(root==null || key.compareTo(root.key)==0)
      return root;
      if( key.compareTo(root.key)<0 )
      return findKey(root.left, key);
      else // strcmp(k, R->key)>0
      return findKey(root.right, key);
   }

   // isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty() {
      return (numPairs == 0) ? true: false;
   }

   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size() {
      return numPairs;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key) {
      // Check if numItems = 0 to avoid calling findKey if not needed
      if (numPairs>0) {
         Node returnNode = findKey(root, key);
         if (returnNode!=null) {
            return returnNode.value;
         }
      }
      // Returns null if Node was not found in list or if numItems = 0
      return null;
   }

   Node findLeftmost(Node root){
      Node temp = root;
      if( temp!=null ) for( ; temp.left!=null; temp=temp.left) ;
      return root;
   }

   // findParent()
   // returns the parent of N in the subtree rooted at R, or returns NULL
   // if N is equal to R. (pre: R != NULL)
   Node findParent(Node N, Node R){
      Node P=null;
      if( N!=R ){
         P = R;
         while( P.left!=N && P.right!=N ){
            if(N.key.compareTo(P.key)<0)
            P = P.left;
            else
            P = P.right;
         }
      }
      return P;
   }

   public void printInOrder(Node root, String returnString) {
      if(root!=null){
         printInOrder(root.left, returnString);
         returnString+=root.key+" "+root.value;
         printInOrder(root.right, returnString);
      }
   }

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException {
      Node node, tempParent, tracer;
      if( findKey(root, key)!=null ){
         System.out.println("Dictionary Error: calling insert() on duplicate key: "+key);
      }
      node = new Node(key, value);
      tempParent = null;
      tracer = root;
      // Loop that finds the deepest parent node on the
      // correct part of the tree
      while( tracer!=null ){
         tempParent = tracer;
         if( key.compareTo(tracer.key)<0 ) {
            tracer = tracer.left;
         }
         else tracer = tracer.right;
      }
      // If the Dictionary is empty, make the new node the Root
      if(tempParent==null) {
         root = node;
      }
      //
      else if( key.compareTo(tempParent.key)<0 ) {
         tempParent.left = node;
      }
      else {
         tempParent.right = node;
      }
      numPairs++;
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)==null
   public void delete(String key) throws KeyNotFoundException {
      Node node, parent, temp;
      if(root==null){
         System.out.println("Dictionary Error: calling delete() on null Dictionary reference");
      }
      node = findKey(root, key);
      if( node==null ){
         System.out.println("Dictionary Error: calling delete() on null Dictionary reference");
      }
      if( node.left==null && node.right==null ){  // case 1 (no children)
         if( node==root ){
            root = null;
         }else{
            parent = findParent(node, root);
            if( parent.right==node ) parent.right = null;
            else parent.left = null;
         }
      }else if( node.right==null ){  // case 2 (left but no right child)
         if( node==root ){
            root = node.left;
         }else{
            parent = findParent(node, root);
            if( parent.right==node ) parent.right = node.left;
            else parent.left = node.left;
         }
      }else if( node.left==null ){  // case 2 (right but no left child)
         if( node==root ){
            root = node.right;
         }else{
            parent = findParent(node, root);
            if( parent.right==node ) parent.right = node.right;
            else parent.left = node.right;
         }
      }else{                     // case3: (two children: N->left!=null && N->right!=null)
         temp = findLeftmost(node.right);
         node.key = temp.key;
         node.value = temp.value;
         parent = findParent(temp, node);
         if(parent.right==temp) parent.right = temp.right;
         else parent.left = temp.right;
      }
      numPairs--;
   }

   // makeEmpty()
   // pre: none
   public void makeEmpty() {
      root = null;
      numPairs = 0;
   }

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString() {
      String returnString = "";
      if(root!=null){
         printInOrder(root.left, returnString);
         returnString+=root.key+" "+root.value;
         printInOrder(root.right, returnString);
      }
   }

}
