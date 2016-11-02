class DictionaryTest {
  public static void main(String[] args) {
    Dictionary dict = new Dictionary();
    dict.insert("1","alpha");
    dict.insert("2","bravo");
    dict.insert("3","charlie");
    dict.insert("4","delta");
    dict.insert("5","echo");
    // dict.makeEmpty();
    // System.out.println(dict.lookup("apple"));
    // System.out.println(dict.isEmpty());
    System.out.println(dict.size());
    System.out.println(dict.toString());
    dict.delete("4");
    System.out.println(dict.toString());
    // System.out.println(dict.lookup("1"));
    // System.out.println(dict.lookup("2"));
  }
}
