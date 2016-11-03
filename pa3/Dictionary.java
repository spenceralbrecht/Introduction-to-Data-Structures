// Spencer Albrecht
// salbrech
// PA3
// Dictionary.java
// All of the methods needed to implement a dictionary
// with key value pairs as represented by a doubly linked
// list.
public class Dictionary implements DictionaryInterface {

  // Private inner Node class
  private class Node {
    // Fields that store the parameters that each node will contain
    String key;
    String value;
    Node next;
    Node last;

    // Constructor for node with a key and value
    Node(String key, String value){
      this.value = value;
      this.key = key;
      this.next = null;
      this.last = null;
    }

    // Constructor without parameters so that the
    // nodes created to traverse the linked list
    // don't need to be created with key and value
    // parameters
    Node() {
      this.value = null;
      this.key = null;
      this.next = null;
      this.last = null;
    }
  }

  // Fields for the Dictionary class
  private Node head;     // reference to first Node in Dictionary
  private Node tail;     // reference to last Node in Dictionary
  private int numItems;  // number of items in current Dictionary

  // Constructor for a Dictionary object. Initialized with
  // default fields
  public Dictionary() {
    head = null;
    tail = null;
    numItems = 0;
  }

  // Private method that is used by lookup, insert, and delete
  // to find and return a node with a specific key
  private Node findKey(String key) {
    // If numItems < 0, the key will not be found
    if (numItems > 0) {
      // Create a new node that will travel through the linked
      // list checking for matches
      Node finder = new Node();
      finder = head;
      // Loop through each node until the end of the list is reached
      while(finder!=null) {
        // Check the key on each node and compary to target.
        // Return Node object if found
        if (finder.key.equals(key)) {
          return finder;
        }
        else {
          finder = finder.next;
        }
      }
    }
    // Only return null if the key was not found
    return null;
  }

  // isEmpty()
  // pre: none
  // returns true if this Dictionary is empty, false otherwise
  public boolean isEmpty() {
    return (numItems == 0) ? true: false;
  }

  // size()
  // pre: none
  // returns the number of entries in this Dictionary
  public int size() {
    return numItems;
  }

  // lookup()
  // pre: none
  // returns value associated key, or null reference if no such key exists
  public String lookup(String key) {
    // Check if numItems = 0 to avoid calling findKey if not needed
    if (numItems>0) {
      Node returnNode = findKey(key);
      if (returnNode!=null) {
        return returnNode.value;
      }
    }
    // Returns null if Node was not found in list or if numItems = 0
    return null;
  }

  // insert()
  // inserts new (key,value) pair into this Dictionary
  // pre: lookup(key)==null
  public void insert(String key, String value) throws DuplicateKeyException {
    // Check if a Node with that key is already in the list,
    // if it is, throw exception
    if (lookup(key)!=null) {
      System.out.println("Throwing DKE");
      throw new DuplicateKeyException("cannot insert duplicate keys");
    }
    else {
      Node newNode = new Node(key, value);
      // Properly insert at head of current linked list
      if (numItems>0) {
        newNode.next = head;
        head.last = newNode;
      }
      // If this is the first Node in the list, set next and
      // last appropriately
      else {
        newNode.next = null;
        tail = newNode;
      }
      // Because we are inserting at head, our new Node's .last field
      // will always be set to null
      newNode.last = null;
      head = newNode;
      numItems++;
    }
  }

  // delete()
  // deletes pair with the given key
  // pre: lookup(key)==null
  public void delete(String key) throws KeyNotFoundException {
    // Throws exception if the key we are trying to delete is
    // not in the list
    if (lookup(key)==null) {
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    else {
      // Create a new Node a set it equal to the Node we want to delete
      Node tempNode = new Node();
      tempNode = findKey(key);
      // Special case for if we are deleting the last Node in the list
      if (tail==tempNode) {
        tail = tempNode.last;
        tempNode.last.next = null;
      }
      // Special case for deleting the first Node in the list
      else if (head==tempNode) {
        head = tempNode.next;
        tempNode.next.last = null;
      }
      // General case for deleting a Node inside the list
      else {
        tempNode.last.next = tempNode.next;
        tempNode.next.last = tempNode.last;
      }
      numItems--;
    }
  }

  // makeEmpty()
  // pre: none
  public void makeEmpty() {
    head = null;
    tail = null;
    numItems = 0;
  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
  public String toString() {
    String returnString = "";
    // Returns the empty string if numItems < 0
    if (numItems > 0) {
      Node finder = new Node();
      finder = tail;
      // Loop through list backwards to print in the order that
      // Nodes were added to the list and concatenate key and
      // value to the returnString
      while(finder!=null) {
        returnString += finder.key+" "+finder.value+"\n";
        finder = finder.last;
      }
    }
    return returnString;
  }
}
