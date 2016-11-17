//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// ListTest.java
// Lab 6
// Custom test methods for List<T> ADT
//-----------------------------------------------------------------------------
class ListTest {
   public static void main(String[] args) {
      List<String> stringList = new List<String>();
      stringList.add(1, "apple");
      stringList.add(2, "banana");
      stringList.add(3, "cat");
      System.out.println(stringList);
      System.out.println("Size: "+stringList.size());
      stringList.remove(3);
      System.out.println(stringList);
      System.out.println("Size: "+stringList.size());
      System.out.println("Is empty: "+stringList.isEmpty());
      //stringList.removeAll();
      //System.out.println("Size after removeAll: "+stringList.size());


      List<Double> doubleList = new List<Double>();
      doubleList.add(1,1.1);
      doubleList.add(2,2.2);
      doubleList.add(3,3.3);
      doubleList.add(4,4.4);
      doubleList.add(5,5.5);
      System.out.println(doubleList);
      System.out.println("Size: "+doubleList.size());
      doubleList.remove(4);
      System.out.println(doubleList);
      System.out.println("Size: "+doubleList.size());
      System.out.println("Is empty: "+doubleList.isEmpty());
      //doubleList.removeAll();
      //System.out.println("Size after removeAll: "+stringList.size());
      System.out.println("Lists are equal: "+stringList.equals(doubleList));
   }
}
