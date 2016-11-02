class DictionaryTest {
  public static void main(String[] args) {
    Dictionary dict = new Dictionary();
    // dict.insert("1","alpha");
    // dict.insert("2","bravo");
    // dict.insert("3","charlie");
    // dict.insert("4","delta");
    // dict.insert("5","echo");
    // dict.makeEmpty();
    // System.out.println(dict.lookup("apple"));
    // System.out.println(dict.isEmpty());
    dict.insert("1","a");
    dict.insert("2","b");
    dict.insert("3","c");
    dict.insert("4","d");
    dict.insert("5","e");
    dict.insert("6","f");
    dict.insert("7","g");
    System.out.println(dict);
    // System.out.println("Dictionary size is "+dict.size());
    // System.out.println(dict.toString());
    // dict.delete("4");
    // System.out.println(dict.toString());
    // System.out.println(dict.lookup("1"));
    // System.out.println(dict.lookup("2"));
  }
}
