import java.util.Arrays;

class pa1 {
   public static void main(String[] args) {
      int[] forward = {1,2,3,4,5,6,7,8,9,10};
      int[] backward = new int[forward.length];

      // Testing Statements for reverseArray1
      // System.out.println("Reverse Part of the Array");
      // reverseArray1(forward, 4, backward);
      // System.out.println(Arrays.toString(backward));
      // System.out.println("Reverse the Whole Array");
      // reverseArray1(forward, forward.length, backward);
      // System.out.println(Arrays.toString(backward));

      // Testing Statements for reverseArray2
      // System.out.println("Reverse Part of the Array");
      // reverseArray2(forward, 4, backward);
      // System.out.println(Arrays.toString(backward));
      // System.out.println("Reverse the Whole Array");
      // reverseArray2(forward, forward.length, backward);
      // System.out.println(Arrays.toString(backward));

      // Testing Statements for reverseArray3
      // System.out.println("Reverse Part of the Array");
      // reverseArray3(forward, 0, 3);
      // System.out.println(Arrays.toString(forward));
      // System.out.println("Reverse the Whole Array");
      // reverseArray3(forward, 0, forward.length-1);
      // System.out.println(Arrays.toString(forward));
   }

   static void reverseArray1(int[] X, int n, int[] Y) {
      if (n == 0) {
         return;
      }
      else {
         Y[Y.length-n] = X[n-1];
         reverseArray1(X, n-1, Y);
      }
   }

   static void reverseArray2(int[] X, int n, int[] Y) {
      if (n == 0) {
         return;
      }
      else {
         Y[n-1] = X[X.length-n];
         reverseArray2(X, n-1, Y);
      }
   }

   static void reverseArray3(int[] X, int i, int j) {
      if (i+1 == j) {
         int temp = X[i];
         X[i] = X[j];
         X[j] = temp;
         return;
      }
      else {
         int temp = X[i];
         X[i] = X[j];
         X[j] = temp;
         reverseArray3(X, i+1, j-1);
      }
   }

   static void maxArrayIndex(int[] X, int p, int r) {

   }

   static void minArrayIndex(int[] X, int p, int r) {

   }
}
