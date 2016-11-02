public class Dictionary implements DictionaryInterface {

  // private inner Node class
  private class Node {
    String key;
    String value;
    Node next;
    Node last;

    Node(String key, String value){
      this.value = value;
      this.key = key;
      this.next = null;
      this.last = null;
    }

    Node() {
      this.value = null;
      this.key = null;
      this.next = null;
      this.last = null;
    }
  }

  // fields for the Dictionary class
  private Node head;     // reference to first Node in Dictionary
  private int numItems;  // number of items in current Dictionary

  public Dictionary() {
    head = null;
    numItems = 0;
  }

  private Node findKey(String key) {
    if (numItems > 0) {
      Node finder = new Node();
      finder = head;
      // System.out.println("we are searching for value "+key);
      while(finder!=null) {
        // System.out.println("finder value is equal to "+finder.key);
        // System.out.println("loop ran once");
        if (finder.key.equals(key)) {
          return finder;
        }
        else {
          finder = finder.next;
        }
      }
    }
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
    // System.out.println("findKey returned "+findKey(key));
    Node returnNode = findKey(key);
    System.out.println("return node initialized to null or value");
    System.out.println("numItems = "+numItems);
    if (numItems>0 && returnNode!=null) {
      // Node returnNode = findKey(key);
      System.out.println("returnNode.value = "+returnNode.value);
      System.out.println("Lookup is running");
      return returnNode.value;
    }
    else {
      return null;
    }
  }

  // insert()
  // inserts new (key,value) pair into this Dictionary
  // pre: lookup(key)==null
  public void insert(String key, String value) throws DuplicateKeyException {
    if (lookup(key)!=null) {
      System.out.println("insert is throwing exceptions");
      throw new DuplicateKeyException();
    }
    else {
      // System.out.println("insert is running");
      Node newNode = new Node(key, value);
      if (numItems>0) {
        newNode.next = head;
        head.last = newNode;
      }
      else {
        newNode.next = null;
      }
      newNode.last = null;
      head = newNode;
      numItems++;
    }
  }

  // delete()
  // deletes pair with the given key
  // pre: lookup(key)==null
  public void delete(String key) throws KeyNotFoundException {
    if (lookup(key)==null) {
      throw new KeyNotFoundException();
    }
    else {
      Node tempNode = new Node();
      tempNode = findKey(key);
      tempNode.last.next = tempNode.next;
      numItems--;
    }
  }

  // makeEmpty()
  // pre: none
  public void makeEmpty() {
    head = null;
    numItems = 0;
  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
  public String toString() {
    String returnString = "";
    if (numItems > 0) {
      Node finder = new Node();
      finder = head;
      // System.out.println("we are searching for value "+key);
      while(finder!=null) {
        returnString += finder.key+" "+finder.value+"\n";
        finder = finder.next;
      }
    }
    return returnString;
  }
}
