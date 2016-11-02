class DictionaryTest {
  public static void main(String[] args) {
    Dictionary dict = new Dictionary();
    dict.insert("1","apple");
    dict.insert("2","banana");
    dict.makeEmpty();
    // System.out.println(dict.lookup("apple"));
    System.out.println(dict.isEmpty());
    System.out.println(dict.size());
    // System.out.println(dict.lookup("1"));
    // System.out.println(dict.lookup("2"));
  }
}
